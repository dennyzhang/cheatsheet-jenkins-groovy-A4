//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: string-to-json.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// https://gist.github.com/hayderimran7/50cb1244cc1e856873a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-03-14 15:29:43>
//-------------------------------------------------------------------

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// Create a jenkins user
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("jenkinsadmin","password1234")
instance.setSecurityRealm(hudsonRealm)
