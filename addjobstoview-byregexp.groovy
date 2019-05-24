#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: addjobstoview-byregexp.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-23 14:37:35>
//-------------------------------------------------------------------
// imports
import jenkins.model.Jenkins
import hudson.model.ListView
import hudson.model.View

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

def viewName = 'MyTest'
jenkins.addView(new ListView(viewName))
myView = hudson.model.Hudson.instance.getView(viewName)
for (item in Jenkins.instance.projects.collect()) {
    if (item.name ==~ /.*Test.*/ ) {
        myView.doAddJobToView(item.name)
    }
}

// Add by regexp
viewName = 'Integration'
jenkins.addView(new ListView(viewName))
myView = hudson.model.Hudson.instance.getView(viewName)
myView.setIncludeRegex(".*Integration.*")
