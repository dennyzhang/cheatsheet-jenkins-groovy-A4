#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: jenkinsfile-parallelly.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:42:45>
//-------------------------------------------------------------------
stage('stage1') {
    echo 'Hello World'
}

stage('stage2') {
    parallel firstBranch: {
        // do something
         build 'dennyjob1'
    }, secondBranch: {
        // do something else
        build job: 'dennyjob2', parameters: [string(name: 'env1', value: 'value3')]
    },
    failFast: true
}

stage('stage3') {
   build 'dennyjob3'
}
