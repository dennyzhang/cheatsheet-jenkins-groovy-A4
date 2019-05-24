#!groovy
//-------------------------------------------------------------------
// @copyright 2018 DennyZhang.com
// Licensed under MIT
// https://www.dennyzhang.com/wp-content/mit_license.txt
//
// File: jenkins-kubernetes-cloud.groovy
// Author : Denny <https://www.dennyzhang.com/contact>
// Link: https://cheatsheet.dennyzhang.com/cheatsheet-jenkins-groovy-a4
// --
// Created : <2018-04-20>
// Updated: Time-stamp: <2019-05-23 22:30:46>
//-------------------------------------------------------------------
// https://github.com/carlossg/jenkins-kubernetes-plugin/blob/master/src/main/java/org/csanchez/jenkins/plugins/kubernetes/KubernetesCloud.java
import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
try {
    def j = Jenkins.getInstance()
        def client = j.clouds[0].connect()
        client.pods().list();
} catch (KubernetesClientException e) {
    println("Error testing connection %s" + e.getMessage())
} catch (Exception e) {
    println("Error testing connection %s" + e.getMessage())
}
