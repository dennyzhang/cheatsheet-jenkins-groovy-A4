// https://gist.github.com/hayderimran7/50cb1244cc1e856873a4

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

// logged-in users can do anything
def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
