#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: docker-cleanup.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:52:13>
//-------------------------------------------------------------------
// https://gist.github.com/rcbop/4acabfb15db6b361698ebd76ce5646e2
node("${params.BUILD_AGENT}") {
    
    stage('Dangling Containers') {
      sh 'docker ps -q -f status=exited | xargs --no-run-if-empty docker rm'
    }

    stage('Dangling Images') {
      sh 'docker images -q -f dangling=true | xargs --no-run-if-empty docker rmi'
    }
    
    stage('Dangling Volumes') {
      sh 'docker volume ls -qf dangling=true | xargs -r docker volume rm'
    }
}

