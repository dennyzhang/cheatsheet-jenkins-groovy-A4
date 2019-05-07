#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: kill-queued-jenkins.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-07 09:53:48>
//-------------------------------------------------------------------
// https://gist.github.com/realityforge/c57eb3d1854320d14252ac881fc6cedf
import hudson.model.*

def q = Jenkins.instance.queue

q.items.each {
  if (it =~ /deploy-to/) {
    q.cancel(it.task)
  }
}

