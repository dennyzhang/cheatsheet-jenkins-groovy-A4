#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: string-to-json.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:42:18>
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
