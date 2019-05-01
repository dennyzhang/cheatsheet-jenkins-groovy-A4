#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: relative-path.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 13:55:52>
//-------------------------------------------------------------------
// https://gist.github.com/ysb33r/5804364
def root= new File('/usr/share')
def full= new File('/usr/share/docs/rpm-4.4')

// Print the relative path of 'full' in relation to 'root'
// Notice that the full path is passed as a parameter to the root.
def relPath= new File( root.toURI().relativize( full.toURI() ).toString() )
