#!groovy

def thinBackupDir = new File(folderName)
if (thinBackupDir.exists() == false) {
   thinBackupDir.mkdirs()
}
