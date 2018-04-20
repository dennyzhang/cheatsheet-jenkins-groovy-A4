# cheatsheet-jenkins-groovy-A4
<a href="https://github.com/DennyZhang?tab=followers"><img align="right" width="200" height="183" src="https://raw.githubusercontent.com/USDevOps/mywechat-slack-group/master/images/fork_github.png" /></a>

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com) [![LinkedIn](https://raw.githubusercontent.com/USDevOps/mywechat-slack-group/master/images/linkedin_icon.png)](https://www.linkedin.com/in/dennyzhang001) <a href="https://www.dennyzhang.com/slack" target="_blank" rel="nofollow"><img src="http://slack.dennyzhang.com/badge.svg" alt="slack"/></a> [![Github](https://raw.githubusercontent.com/USDevOps/mywechat-slack-group/master/images/github.png)](https://github.com/DennyZhang)

File me [tickets](https://github.com/DennyZhang/cheatsheet-jenkins-groovy-A4/issues) or star [the repo](https://github.com/DennyZhang/cheatsheet-jenkins-groovy-A4).

See more CheatSheets from Denny: [#denny-cheatsheets](https://github.com/topics/denny-cheatsheets)

Table of Contents
=================

   * [cheatsheet-jenkins-groovy-A4](#cheatsheet-jenkins-groovy-A4)
      * [Jenkins Pipeline](#jenkins-pipeline)
      * [Array](#array)
      * [String](#string)
      * [Integer](#integer)
      * [Dict/Hashmap/Map](#dicthashmapmap)
      * [Files](#files)
      * [Math](#math)
   * [Code snippets](#code-snippets)
   * [More links](#more-links)

<a href="https://www.dennyzhang.com"><img align="right" width="185" height="37" src="https://raw.githubusercontent.com/USDevOps/mywechat-slack-group/master/images/dns_small.png"></a>

**Groovy CheatSheet**: https://github.com/DennyZhang/cheatsheet-jenkins-groovy-A4

## Jenkins Pipeline
| Name                                      | Comment                                                          |
| :--------------------------------         | --------------------------------------------------------------   |
| Specify parameter to run jobs             | `build job:'job1', parameters:[string(name:'name1', value:va1)]` |
| Run job in different agents               | `node($agent_label) {...}`                                       |
| Use boolean parameter                     | `if (is_true == "false") {...}`                                  |
| Ask for user input                        | `stage('stage2'){ input "OK to go?" }`                           |
| Actively fail current pipeline job        | `error("Build failed because of this and that..")`               |
| Keep going when previous stage has failed | [keep_going_with_errors.groovy](keep_going_with_errors.groovy)   |
| Send slack notification in pipeline       | [slack_notification.groovy](slack_notification.groovy)           |
  
## Array

| Name                           | Comment                                      |
| :----------------------------- | -------------------------------------------- |
| Iterate a list                 | `(1..3).each { println "Number ${it}"}`      |
| Iterate a list                 | `for(item in [1,2,3,4]){ println item }`     |
| Add item to list               | `def alist = [10, 9, 8]; alist << 7`         |
| List size                      | `def alist = [10, 9, 8]; alist.size()`       |
  
## String

| Name                        | Comment                                              |
| :------------------------   | ---------------------------------------------------- |
| Print stdout                | `echo 'Action is done'`                              |
| Print stdout                | `println "Hello World"`                              |
| Split string with delimiter | `'1128-2'.tokenize( '-' )`                           |

## Integer

| Name             | Comment                                          |
| :------------    | ------------------------------------------------ |
| Basic caculation | `def a = 3, b = 7; println "$a + $b = ${a + b}"` |
  
## Dict/Hashmap/Map

| Name                 | Comment                                        |
| :------------------- | ---------------------------------------------- |
| Create a map         | `def m = ['fruit':'Apple', 'veggie':'Carrot']` |
| Add an item to map   | `m.put('denny','hello')`                       |
  
## Files

| Name                            | Comment                                   |
| :------------------------------ | ----------------------------------------- |
| Read file content as a variable | `def content = readFile("/tmp/test.txt")` |
  
## Math

| Name          | Comment                  |
| :------------ | ------------------------ |

# Code snippets

# More links
- groovy-lang.org: http://groovy-lang.org/documentation.html#gettingstarted
- https://jenkins.io/doc/

TODO: Need to automatically generate A4 pdf

License: Code is licensed under [MIT License](https://www.dennyzhang.com/wp-content/mit_license.txt).

<a href="https://www.dennyzhang.com"><img align="right" width="201" height="268" src="https://raw.githubusercontent.com/USDevOps/mywechat-slack-group/master/images/denny_201706.png"></a>

<a href="https://www.dennyzhang.com"><img align="right" src="https://raw.githubusercontent.com/USDevOps/mywechat-slack-group/master/images/dns_small.png"></a>
