#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: json-file.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:52:56>
//-------------------------------------------------------------------
// https://gist.github.com/keyle/723181f3a9a59a3f7652

package utils

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

class HDD {

    static save(Object content, String filePath) {
        new File(filePath).write(new JsonBuilder(content).toPrettyString())
    }

    static Object load(String filePath) {
        return new JsonSlurper().parseText(new File(filePath).text)
    }
}
