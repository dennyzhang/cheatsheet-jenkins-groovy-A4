#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: list-all-jobs.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-07 14:14:04>
//-------------------------------------------------------------------
// http://paweloczadly.github.io/dev/2014/07/03/jenkins-groovy-script-list-all-jobs
import hudson.model.*

hudson.model.Hudson.instance.items.findAll{job -> job}.each {
    job -> println("Job: " + job.name)
}
