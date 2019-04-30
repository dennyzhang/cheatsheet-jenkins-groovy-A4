#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: jenkinsfile-sequentially.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:42:50>
//-------------------------------------------------------------------
stage('stage1') {
    echo 'Hello World'
}


stage('stage2') {
   build 'dennyjob1'
}

stage('stage3') {
   build job: 'dennyjob2', parameters: [string(name: 'env1', value: 'value3')]
}
