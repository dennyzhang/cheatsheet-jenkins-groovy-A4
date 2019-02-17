#!groovy

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
