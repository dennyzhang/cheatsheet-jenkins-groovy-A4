#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: timezone.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-02-16 22:04:03>
//-------------------------------------------------------------------
// Configure Jenkins java options
// timezone: Asia/Shanghai, America/New_York, America/Los_Angeles
// https://www.epochconverter.com/timezones
// ENV JENKINS_TIMEZONE "UTC"

def env = System.getenv();
System.setProperty('org.apache.commons.jelly.tags.fmt.timeZone', env['JENKINS_TIMEZONE']);
