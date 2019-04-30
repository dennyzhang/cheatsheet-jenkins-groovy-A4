#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: get-ip-by-hostname.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// https://asoftwareguy.com/2014/04/17/get-the-ip-address-for-a-host-name-in-groovy/
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-04-29 15:42:37>
//-------------------------------------------------------------------
def hostname = 'google.com'
println InetAddress.getByName(hostname).address.collect { it & 0xFF }.join('.')
