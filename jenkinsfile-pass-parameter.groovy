#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: jenkinsfile-pass-parameter.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:42:48>
//-------------------------------------------------------------------
node {
  def seconds = readFile("/tmp/test.txt")

    echo "seconds begin"
    echo seconds
    echo "seconds done"
    parallel firstBranch: {
    // do something
    build job: 'SleepAnHour', parameters: [string(name: 'sleep_seconds', value: seconds)]
  }, secondBranch: {
    // do something else
    build job: 'SleepTwoHours', parameters: [string(name: 'sleep_seconds', value: seconds)]
      },
       failFast: true
    }
