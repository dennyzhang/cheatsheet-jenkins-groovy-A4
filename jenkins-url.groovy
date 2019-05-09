#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: jenkins-url.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-09 11:43:15>
//-------------------------------------------------------------------
// https://gist.github.com/fishi0x01/7c2d29afbaa0f16126eb4d4b35942f76
/*
 * This script configures the Jenkins base URL.
 */

import jenkins.model.JenkinsLocationConfiguration

JenkinsLocationConfiguration location = Jenkins.instance.getExtensionList('jenkins.model.JenkinsLocationConfiguration')[0]
location.url = 'https://jenkins-as-code-poc.devtail.io/'
location.save()

// Solution 2
// https://github.com/docksal/service-jenkins/blob/master/groovy/default-config.groovy
import jenkins.model.*
import hudson.security.*

// Get environment variables.
def env = System.getenv()

// Change location configuration for admin address and Jenkins URL.
// Currently this will override settings changed via Jenkins UI.
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setAdminAddress(env.JENKINS_EMAIL)
jenkinsLocationConfiguration.setUrl(env.JENKINS_URL)
jenkinsLocationConfiguration.save()
