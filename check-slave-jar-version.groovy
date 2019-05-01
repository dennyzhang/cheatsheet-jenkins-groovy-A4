#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: check-slave-jar-version.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:51:48>
//-------------------------------------------------------------------
// https://gist.github.com/ctran/8f82adedaa23ea924c52
import jenkins.model.*
import hudson.remoting.Launcher
import hudson.slaves.SlaveComputer

def expectedVersion = Launcher.VERSION
for (computer in Jenkins.instance.getComputers()) {
  if (! (computer instanceof SlaveComputer)) continue
  if (!computer.getChannel()) continue
	
  def version = computer.getSlaveVersion()
  if (!expectedVersion.equals(version)) {
    println "${computer.name} - expected ${expectedVersion} but got ${version}"
  }
}
