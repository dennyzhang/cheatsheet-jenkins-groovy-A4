#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: slack_notification.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Description :
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:44:34>
//-------------------------------------------------------------------
// https://stackoverflow.com/questions/39140191/how-to-send-slack-notification-after-jenkins-pipeline-build-failed
// https://jenkins.io/doc/pipeline/steps/slack/
// https://jenkins.io/blog/2016/07/18/pipeline-notifications/
node(agent_label) {
   
   //println testsuites
   stage('Preparation') {
      git (branch: 'master',
           credentialsId: 'github_key',
           url: 'git@github.com:DennyZhang/fake.git')
      if (run_deployment_before_test == "true") {
          build job:'DeploySandboxEnv', 
              parameters:[string(name:'initialize_system', value:initialize_system),
                          string(name:'whether_populatefakedata', value:whether_populatefakedata),
                          string(name:'whether_populaterealdata', value:whether_populaterealdata)]
      }
   }
   
   
  def tc_list = testsuites.tokenize(',')
     tc_list.each {
        try {
            stage("${it}") {
               println "============ Test ${it}"
               sh "mvn com.smartbear.soapui:soapui-maven-plugin:5.1.2:test -Dmdm.test.suite=\"${it}\""
            }
        } catch (e) {
            result = "FAIL"
        }
   }

  stage('CollectResult') {
      if (result == "FAIL") {
         buildStatus = result
         def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
         def summary = "${subject} (${env.BUILD_URL})"
         color = 'RED'
         colorCode = '#FF0000'
         slackSend (color: colorCode, message: summary)
         error("Job has failed")
      }
  }
}
