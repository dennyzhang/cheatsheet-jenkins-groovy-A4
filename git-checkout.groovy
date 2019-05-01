#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: git-checkout.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:52:31>
//-------------------------------------------------------------------
// https://gist.github.com/lferro9000/471ae1a98267e20530d989f64f5290ee
#!/usr/bin/env groovy

node('php') {

    stage('Get code from SCM') {
        checkout(
                [$class: 'GitSCM', branches: [[name: '*/#your-dev-branch#']],
                 doGenerateSubmoduleConfigurations: false,
                 extensions: [],
                 submoduleCfg: [],
                 userRemoteConfigs: [[url: '#your-git-link#']]]
        )
    }

    stage('Composer Install') {
        sh 'composer install'
    }

    stage("PHPLint") {
        sh 'find app -name "*.php" -print0 | xargs -0 -n1 php -l'
    }
}
