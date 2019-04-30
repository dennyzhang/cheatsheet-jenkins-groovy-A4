#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: loop-map.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:42:59>
//-------------------------------------------------------------------
def sampleMap = ['Key#1':'Value#1', 'Key#2':'Value#2']
println sampleMap['Key#1']
node{
    // itertate over stages
    for (key in sampleMap.keySet()){
        val = "${sampleMap[key]}"
        println key
        println val
        stage('Run Key'){
            println "Ran ${key}"
        }

        stage('Ran Value for that Key'){
            println "Ran ${val}"
        }

        stage('Clean Up'){
            println "Ran Some sort of Cleanup"
        }
    }
}
