// https://gist.github.com/hayderimran7/50cb1244cc1e856873a4

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// Create a jenkins user
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("jenkinsadmin","password1234")
instance.setSecurityRealm(hudsonRealm)
