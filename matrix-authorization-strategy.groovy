#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: matrix-authorization-strategy.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:53:20>
//-------------------------------------------------------------------
// http://www.tothenew.com/blog/jenkins-implementing-project-based-matrix-authorization-strategy/
/*
   Configure matrix authorization strategy with permissions for users and
   groups.  This script is idempotent and will only change configuration if
   necessary.

   Example configuration:
       authz_strategy_config = [
           strategy: 'GlobalMatrixAuthorizationStrategy',
           user_permissions: [
               anonymous: ['Job Discover'],
               authenticated: ['Overall Read', 'Job Read', 'View Read'],
               admin: ['Overall Administer']
           ]
       ]

   Available Authorization Strategies:
       GlobalMatrixAuthorizationStrategy
       ProjectMatrixAuthorizationStrategy

   Available user permissions:
       Overall Administer
       Overall Read
       Agent Configure
       Agent Delete
       Agent Create
       Agent Disconnect
       Agent Connect
       Agent Build
       Agent Provision
       Run Delete
       Run Update
       Job Create
       Job Delete
       Job Configure
       Job Read
       Job Discover
       Job Build
       Job Workspace
       Job Cancel
       SCM Tag
       Credentials Create
       Credentials Update
       Credentials View
       Credentials Delete
       Credentials ManageDomains
       Job Move
       View Create
       View Delete
       View Configure
       View Read
       Run Replay

   "Job ViewStatus" permission becomes available after installing the
   embeddable build status plugin.
 */
import jenkins.model.*

import hudson.security.GlobalMatrixAuthorizationStrategy
import hudson.security.Permission
import hudson.security.ProjectMatrixAuthorizationStrategy
import jenkins.model.Jenkins

class Helper implements Serializable {
   static String shortName(Permission p) {
      p.id.tokenize('.')[-2..-1].join(' ')
	 .replace('Hudson','Overall')
	 .replace('Computer', 'Agent')
	 .replace('Item', 'Job')
	 .replace('CredentialsProvider', 'Credentials')
   }

   static Map getCurrentPermissions(Map config = [:]) {
      Map currentPermissions = [:].withDefault { [].toSet() }
      if(!('getGrantedPermissions' in Jenkins.instance.authorizationStrategy.metaClass.methods*.name.sort().unique())) {
	 return currentPermissions
      }
      Closure merger = { Map nmap, Map m ->
	 m.each { k, v ->
	    nmap[k] += v
	 }
      }
      Jenkins.instance.authorizationStrategy.grantedPermissions.collect { permission, userList ->
	 userList.collect { user ->
	    [ (user): shortName(permission) ]
	 }
      }.flatten().each merger.curry(currentPermissions)
      currentPermissions
   }

   static boolean isConfigurationEqual(Map config) {
      Map currentPermissions = getCurrentPermissions(config)
      Jenkins.instance.authorizationStrategy.class.name.endsWith(config['strategy']) &&
      !(false in config['user_permissions'].collect { k, v -> currentPermissions[k] == v.toSet() }) &&
      currentPermissions.keySet() == config['user_permissions'].keySet()
   }

   static boolean isValidConfig(def config, List<String> validPermissions) {
      Map currentPermissions = getCurrentPermissions()
      config instanceof Map &&
      config.keySet().containsAll(['strategy', 'user_permissions']) &&
      config['strategy'] &&
      config['strategy'] instanceof String &&
      config['strategy'] in ['GlobalMatrixAuthorizationStrategy', 'ProjectMatrixAuthorizationStrategy'] &&
      config['user_permissions'] &&
      !(false in config['user_permissions'].collect { k, v ->
	 k instanceof String &&
	 (v instanceof List || v instanceof Set) &&
	 !(false in v.collect {
	    validPermissions.contains(it)
	 })
      })
   }
}

Map<String, Permission> permissionIds = Permission.all.findAll { permission ->
   List<String> nonConfigurablePerms = ['RunScripts', 'UploadPlugins', 'ConfigureUpdateCenter']
   permission.enabled &&
      !permission.id.startsWith('hudson.security.Permission') &&
      !(true in nonConfigurablePerms.collect { permission.id.endsWith(it) })
}.collect { permission ->
   [ (Helper.shortName(permission)): permission ]
}.sum()

/**
 * MAIN EXECUTION
 */

if(!binding.hasVariable('authz_strategy_config')) {
   authz_strategy_config = [
      strategy: 'GlobalMatrixAuthorizationStrategy',
      user_permissions: [
	 'authenticated': ['Overall Read'],
	 'zdenny': ['Overall Administer']
      ]
   ]
}

if(!Helper.isValidConfig(authz_strategy_config, permissionIds.keySet().toList())) {
   println([
      'Skip configuring matrix authorization strategy because no valid config was provided.',
      'Available Authorization Strategies:\n    GlobalMatrixAuthorizationStrategy\n    ProjectMatrixAuthorizationStrategy',
      "Available Permissions:\n    ${permissionIds.keySet().join('\n    ')}"
   ].join('\n'))
   return
}

if(Helper.isConfigurationEqual(authz_strategy_config)) {
   println "Nothing changed.  ${authz_strategy_config['strategy']} authorization strategy already configured."
   return
}

println "Configuring authorization strategy ${authz_strategy_config['strategy']}"

def authz_strategy = Class.forName("hudson.security.${authz_strategy_config['strategy']}").newInstance()

// build the permissions in the strategy
authz_strategy_config['user_permissions'].each { user, permissions ->
   permissions.each { p ->
      authz_strategy.add(permissionIds[p], user)
      println "    For user ${user} grant permission ${p}."
   }
}

// configure global authorization
Jenkins.instance.authorizationStrategy = authz_strategy

// save settings to persist across restarts
Jenkins.instance.save()
