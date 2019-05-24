// https://stackoverflow.com/questions/44501596/jenkins-disable-cli-over-remoting-via-a-groovy-script
import jenkins.model.Jenkins

jenkins.model.Jenkins.instance.getDescriptor("jenkins.CLI").get().setEnabled(false)
Jenkins.instance.save()
