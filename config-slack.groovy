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
// Updated: Time-stamp: <2019-04-30 16:07:20>
//-------------------------------------------------------------------
// https://github.com/jenkinsci/slack-plugin/blob/master/src/main/java/jenkins/plugins/slack/SlackNotifier.java
import jenkins.model.Jenkins
import jenkins.plugins.slack.SlackNotifier.*

// def slack = Jenkins.instance.getDescriptorByType(jenkins.plugins.slack.SlackNotifier.DescriptorImpl)
def slack = Jenkins.instance.getExtensionList('jenkins.plugins.slack.SlackNotifier$DescriptorImpl')[0]

slack.tokenCredentialId = 'slack_token_passwd'
slack.baseUrl = 'https://vmware.slack.com/services/hooks/jenkins-ci'

slack.save()
println 'Slack global settings configured.'
