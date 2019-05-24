// https://wiki.jenkins.io/display/JENKINS/CSRF+Protection
import hudson.security.csrf.DefaultCrumbIssuer
import jenkins.model.Jenkins
 
def instance = Jenkins.instance
instance.setCrumbIssuer(new DefaultCrumbIssuer(true))
instance.save()
