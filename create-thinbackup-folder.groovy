#!groovy
def folderName = '/var/jenkins_home/jobs/backup'

def thinBackupDir = new File(folderName)
if (thinBackupDir.exists() == false) {
   // https://github.com/cloudbees/jenkins-scripts/blob/master/copy-move-diagnosis.groovy#L19
   thinBackupDir.mkdirs()
}

/*
// https://mrhaki.blogspot.com/2009/12/groovy-goodness-delete-non-empty.html
def mainDir = new File('test')
def subDir = new File(mainDir, 'app')
def file = new File(subDir, 'test.txt')
 
subDir.mkdirs()  // Create directories.
file << 'sample'  // Create file and add contents.
 
assert mainDir.exists() && subDir.exists() && file.exists()
 
def result = mainDir.deleteDir()  // Returns true if all goes well, false otherwise.
assert result
assert !mainDir.exists() && !subDir.exists() && !file.exists()
*/    
