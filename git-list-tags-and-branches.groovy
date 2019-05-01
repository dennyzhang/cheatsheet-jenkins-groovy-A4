#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: git-list-tags-and-branches.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:47:01>
//-------------------------------------------------------------------
// https://gist.github.com/eeichinger/9761870
def gettags = ("git ls-remote -t -h ssh://jenkins@<mygitpath>/repo/some.git feature/*").execute()

return gettags.text.readLines()
         .collect { it.split()[1].replaceAll('refs/heads/', '')  }
         .unique()
         .findAll { it.startsWith('<some more pattern>') }
