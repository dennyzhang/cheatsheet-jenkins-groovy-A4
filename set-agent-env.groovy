#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: set-agent-env.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-07 14:07:50>
//-------------------------------------------------------------------
// http://paweloczadly.github.io/dev/2014/07/03/jenkins-groovy-script-set-up-java_home-on-each-agent
import hudson.slaves.*

def javaHome = new EnvironmentVariablesNodeProperty(
    new EnvironmentVariablesNodeProperty.Entry('JAVA_HOME', '/usr/lib/jvm/java-7-oracle-amd64'))

hudson.model.Hudson.instance.slaves.each { it.nodeProperties.add(javaHome) }    
