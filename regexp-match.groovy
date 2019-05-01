#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: regexp-match.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:53:27>
//-------------------------------------------------------------------
// https://gist.github.com/EwanDawson/2407215
matcher = "Hello world v1.01" =~ /.* v(\S*)/
if (matcher.matches()) version = matcher[0][1]
assert version == "1.01"

// We can make this a little tidier using the 'with' method
version = ("Hello world v1.01" =~ /.* v(\S*)/).with { matches() ? it[0][1] : null }
assert version == "1.01"
