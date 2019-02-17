#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: jenkins-views.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-02-16 22:03:51>
//-------------------------------------------------------------------
// imports
import jenkins.model.Jenkins
import hudson.model.ListView
import hudson.model.View

// get Jenkins instance
Jenkins jenkins = Jenkins.getInstance()

def viewName = ''

viewName = 'CIAutoMaintain'
jenkins.addView(new ListView(viewName))
myView = hudson.model.Hudson.instance.getView(viewName)
for(item in ['CleanOldArtifact', 'CreateTestEnv']) {
    myView.doAddJobToView(item)
}

// save current Jenkins state to disk
jenkins.save()

// Set default view
View welcome_view = jenkins.getView('CIAutoMaintain')
View primary_view = jenkins.getPrimaryView()
jenkins.setPrimaryView(welcome_view)
