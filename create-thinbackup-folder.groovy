#!groovy
def folderName = '/var/jenkins_home/jobs/backup'

def thinBackupDir = new File(folderName)
if (thinBackupDir.exists() == false) {
   thinBackupDir.mkdirs()
}
