#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: get-env.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-30 15:27:12>
//-------------------------------------------------------------------
// http://www.mytechtoday.com/2009/01/read-environment-variables-with-groovy.html
def env = System.getenv()
// Print all the environment variables.

env.each{
println it
} 
// You can also access the specific variable, say 'username', as show below 
String user= env['USERNAME']

// https://stackoverflow.com/questions/31707667/how-to-access-jenkins-environment-variables-with-dsl

def envVars = Jenkins.instance.getGlobalNodeProperties()[0].getEnvVars()
println envVars['myVar']

/*
import hudson.model.*;
import jenkins.model.*;

Thread.start {
      sleep 10000
      println "--> setting agent port for jnlp"
      def env = System.getenv()
      int port = env['JENKINS_SLAVE_AGENT_PORT'].toInteger()
      Jenkins.instance.setSlaveAgentPort(port)
      println "--> setting agent port for jnlp... done"
}
*/
