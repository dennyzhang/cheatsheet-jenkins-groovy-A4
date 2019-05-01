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
// Updated: Time-stamp: <2019-05-01 16:52:52>
//-------------------------------------------------------------------
// https://gist.github.com/fishi0x01/7c2d29afbaa0f16126eb4d4b35942f76
/*
 * This script configures the Jenkins base URL.
 */

import jenkins.model.JenkinsLocationConfiguration

JenkinsLocationConfiguration location = Jenkins.instance.getExtensionList('jenkins.model.JenkinsLocationConfiguration')[0]
location.url = 'https://jenkins-as-code-poc.devtail.io/'
location.save()
