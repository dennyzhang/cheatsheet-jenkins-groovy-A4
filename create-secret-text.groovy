#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: create-secret-text.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:51:58>
//-------------------------------------------------------------------
// https://gist.github.com/chrisvire/383a2c7b7cfb3f55df6a
import jenkins.model.*
import hudson.util.Secret
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import org.jenkinsci.plugins.plaincredentials.impl.*

domain = Domain.global()
store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

secretText = new StringCredentialsImpl(
CredentialsScope.GLOBAL,
"secret-text",
"Secret Text Description",
Secret.fromString("some secret text goes here"))

store.addCredentials(domain, secretText)
