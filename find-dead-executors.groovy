#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: find-dead-executors.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 14:09:37>
//-------------------------------------------------------------------
// https://gist.github.com/malonem/5045386
// get handle to build output
def config = new HashMap()
def bindings = getBinding()
config.putAll(bindings.getVariables())
def out = config['out']

for (aSlave in hudson.model.Hudson.instance.slaves) {
  // check if executor is dead
  execList = aSlave.getComputer().getExecutors()      
  for( exec in execList ) {
    if (exec.getCauseOfDeath()) {
      println("\tSlave ${aSlave.name} has a dead executor.")
      println("Error:")
      exec.getCauseOfDeath().printStackTrace(out) 
      println('\n')
      println("\tRemoving Dead Executor.")
      exec.doYank()
    }
  } 
}
