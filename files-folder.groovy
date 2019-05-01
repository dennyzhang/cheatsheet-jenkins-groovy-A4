#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: files-folder.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-01 16:52:26>
//-------------------------------------------------------------------
import groovy.io.FileType

// Create folder if missing
def folderName = '/var/jenkins_home/jobs/backup'

def thinBackupDir = new File(folderName)
if (thinBackupDir.exists() == false) {
   // https://github.com/cloudbees/jenkins-scripts/blob/master/copy-move-diagnosis.groovy#L19
   thinBackupDir.mkdirs()
}

////////////////////////////////////////////////////////////////////////////////
// Create folders and files
// https://mrhaki.blogspot.com/2010/04/groovy-goodness-working-on-files-or.html
// First create sample dirs and files.
(1..3).each {
 new File("dir$it").mkdir()
}
(1..3).each {
 def file = new File("file$it")
 file << "Sample content for ${file.absolutePath}"
}

////////////////////////////////////////////////////////////////////////////////
// Create files with different methods
// https://mrhaki.blogspot.com/2009/08/groovy-goodness-working-with-files.html
// Normal way of creating file objects.
def file1 = new File('groovy1.txt')
def file2 = new File('groovy2.txt')
def file3 = new File('groovy3.txt')
 
// Writing to the files with the write method:
file1.write 'Working with files the Groovy way is easy.\n'
 
// Using the leftShift operator:
file1 << 'See how easy it is to add text to a file.\n'
 
// Using the text property:
file2.text = '''We can even use the text property of
a file to set a complete block of text at once.'''
 
// Or a writer object:
file3.withWriter('UTF-8') { writer ->
    writer.write('We can also use writers to add contents.')
}

////////////////////////////////////////////////////////////////////////////////
// Reading contents of files to an array:
def lines = file1.readLines()
assert 2 == lines.size()
assert 'Working with files the Groovy way is easy.' == lines[0]

// Or we read with the text property:
assert 'We can also use writers to add contents.' == file3.text

     
////////////////////////////////////////////////////////////////////////////////
// Delete all files:
// https://mrhaki.blogspot.com/2009/08/groovy-goodness-working-with-files.html
files.each { new File(it).delete() }

////////////////////////////////////////////////////////////////////////////////
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
