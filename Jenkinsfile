#!/usr/bin/env groovy
import groovy.json.JsonSlurper;

def xssh = { cmd -> "ssh -i /home/jenkins/.ssh/${params.ssh_key} ${params.ssh_user}@${params.ssh_server} " + cmd }
def xscp = { file -> "scp -i /home/jenkins/.ssh/${params.ssh_key} target/"+ file +" ${params.ssh_user}@${params.ssh_server}:~/" + xdir}

pipeline {

    // tools used
    tools {
        maven 'apache-maven-3.3.9'
        jdk 'jdk1.8.131'
    }

    // parameters that can be passed in
    parameters {
        string(name: 'ssh_key', defaultValue: 'id_rsa_njs01',description: 'shh keyfilename, should be placed in /home/jenkins/.ssh/')
        string(name: 'ssh_user', defaultValue: 'nodejs')
        string(name: 'ssh_server', defaultValue: 'iwks-hop-cps-njs01.iwedev.local')
        string(name: 'develop_dir', defaultValue: 'entities/develop')
        string(name: 'develop_port', defaultValue: '8080')
        string(name: 'master_dir', defaultValue: 'entities/master')
        string(name: 'master_port', defaultValue: '8081')
    }

	agent any

    // global options for Jenkins Job
	options {
        buildDiscarder(logRotator(numToKeepStr:'5'))
        timeout(time: 5, unit: 'MINUTES')
    }

    stages {

        stage('Build, Test, Package'){
            steps {
                script {
                    commitid = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
                    def workspacePath = pwd()
                    sh "echo ${commitid} > ${workspacePath}/expectedCommitid.txt"
                    sh "mvn clean package -Dcommitid=${commitid}"
                }
            }
        }

        stage('Publish Results'){
            steps {
                script {
                    step([$class: 'JUnitResultArchiver', testResults:'target/surefire-reports/*.xml'])
                }
            }
        }

        stage('Sonar Scan'){
            steps {
                script {
                    def sonarHome = tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                    sh "cat sonar-project.properties"
                    sh "${sonarHome}/bin/sonar-scanner -e -Dsonar.host.url=http://127.0.0.1:8099"
                }
            }
        }

}
