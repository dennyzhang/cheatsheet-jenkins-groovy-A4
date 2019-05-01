#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: delete-job-by-regexp.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:52:03>
//-------------------------------------------------------------------
// https://gist.github.com/nextrevision/d11b2df4e8b229c6855b
import jenkins.model.*

def matchedJobs = Jenkins.instance.items.findAll { job ->
    job.name =~ /my_regex_here/
}
    
matchedJobs.each { job ->
    println job.name
    //job.delete()
}
