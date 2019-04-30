#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: split-string.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:43:13>
//-------------------------------------------------------------------
String ip_hostname_list = """ 138.197.192.172  myenv-do-jenkins
   138.68.254.56  myenv-do-lb-1
  138.68.254.215  myenv-do-lb-2
   165.227.8.194  myenv-do-cb-01
  138.197.215.93  myenv-do-cb-02   """;

def l = ip_hostname_list.split("\n")
print l.join("a")
