//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: keep_going_with_errors.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Description :
// https://stackoverflow.com/questions/36852310/show-a-jenkins-pipeline-stage-as-failed-without-failing-the-whole-job
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2018-04-20 08:34:49>
//-------------------------------------------------------------------
////////////////////////////////////////////////////////////////////////////////
// Stages with shell commands
def tc_list = testsuites.tokenize(',')
  tc_list.each {
  try {
    stage("${it}") {
      println "============ Test ${it}"
      sh "mvn com.smartbear.soapui:soapui-maven-plugin:5.1.2:test -Dmdm.test.suite=\"${it}\""
    }
  } catch (e) {
    result = "FAIL"
  }
}

////////////////////////////////////////////////////////////////////////////////
// Stages with jenkins jobs
try {
  stage('end-to-end-tests') {
    node {
      def e2e = build job:'end-to-end-tests', propagate: false
        result = e2e.result
        if (result.equals("SUCCESS")) {
        } else {
          sh "exit 1" // this fails the stage
        }
    }
  }
 } catch (e) {
  result = "FAIL" // make sure other exceptions are recorded as failure too
    }

stage('deploy') {
  if (result.equals("SUCCESS")) {
    build 'deploy'
    } else {
       echo "Cannot deploy without successful build" // it is important to have a deploy stage even here for the current visualization
    }
}
////////////////////////////////////////////////////////////////////////////////
// File: keep_going_with_errors.groovy ends