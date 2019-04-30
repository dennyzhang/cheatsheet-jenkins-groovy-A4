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
// Updated: Time-stamp: <2019-04-29 15:46:49>
//-------------------------------------------------------------------
// https://stackoverflow.com/questions/38273070/groovy-script-to-apply-kubernetes-cloud-config-in-jenkins
import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

def j = Jenkins.getInstance()

def k = new KubernetesCloud(
  'kubernetes',
  null,
  'https://192.168.150.100:8443',
  '',
  'http://40.0.4.3:8080',
  '10', 0, 0, 5
)
k.setSkipTlsVerify(true)

j.clouds.replace(k)
j.save()
