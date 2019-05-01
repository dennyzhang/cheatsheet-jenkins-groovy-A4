#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: manage-jenkins-jobs.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:53:08>
//-------------------------------------------------------------------
// https://github.com/peterjenkins1/jenkins-scripts/blob/master/add-job.groovy
// Adds a pipeline job to jenkins
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import com.cloudbees.hudson.plugins.folder.*

// Bring some values in from ansible using the jenkins_script modules wierd "args" approach (these are not gstrings)
String folderName = "$folderName"
String jobName = "$jobName"
String jobScript = "$jobScript"
String gitRepo = "$gitRepo"
String gitRepoName = "$gitRepoName"
String credentialsId = "$credentialsId"

Jenkins jenkins = Jenkins.instance // saves some typing

// Get the folder where this job should be
def folder = jenkins.getItem(folderName)
// Create the folder if it doesn't exist
if (folder == null) {
  folder = jenkins.createProject(Folder.class, folderName)
}

// Create the git configuration
UserRemoteConfig userRemoteConfig = new UserRemoteConfig(gitRepo, gitRepoName, null, credentialsId)

branches = null
doGenerateSubmoduleConfigurations = false
submoduleCfg = null
browser = null
gitTool = null
extensions = []
GitSCM scm = new GitSCM([userRemoteConfig], branches, doGenerateSubmoduleConfigurations, submoduleCfg, browser, gitTool, extensions)

// Create the workflow
FlowDefinition flowDefinition = (FlowDefinition) new CpsScmFlowDefinition(scm, jobScript)

// Check if the job already exists
Object job = null
job = folder.getItem(jobName)
if (job == null) {
  oldJob = jenkins.getItem(jobName)
  if (oldJob.getClass() == WorkflowJob.class) {
    // Move any existing job into the folder
    Items.move(oldJob, folder)
  } else {
    // Create it if it doesn't
    job = folder.createProject(WorkflowJob, jobName)
  }
}
// Add the workflow to the job
job.setDefinition(flowDefinition)

// Set the branch somehow
job.save()
