#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: git-commits-before-fail.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:52:35>
//-------------------------------------------------------------------
// https://gist.github.com/ftclausen/8c46195ee56e48e4d01cbfab19c41fc0
// -*- mode: groovy -*-
// vim: set filetype=groovy :
node( 'some_node' ) {
  stage( "Phase 1" ) {
    sshagent( credentials: [ 'some_creds' ] ) {
      checkout scm
      def lastSuccessfulCommit = getLastSuccessfulCommit()
      def currentCommit = commitHashForBuild( currentBuild.rawBuild )
      if (lastSuccessfulCommit) {
        commits = sh(
          script: "git rev-list $currentCommit \"^$lastSuccessfulCommit\"",
          returnStdout: true
        ).split('\n')
        println "Commits are: $commits"
      }
    }
  }
}

def getLastSuccessfulCommit() {
  def lastSuccessfulHash = null
  def lastSuccessfulBuild = currentBuild.rawBuild.getPreviousSuccessfulBuild()
  if ( lastSuccessfulBuild ) {
    lastSuccessfulHash = commitHashForBuild( lastSuccessfulBuild )
  }
  return lastSuccessfulHash
}

/**
 * Gets the commit hash from a Jenkins build object, if any
 */
@NonCPS
def commitHashForBuild( build ) {
  def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
  return scmAction?.revision?.hash
}
