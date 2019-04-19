#!groovy

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
