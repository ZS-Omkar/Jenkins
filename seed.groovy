pipelineJob('frontend') {
    configure { flowdefinition ->
        flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugin.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugin.git.UserRemoteConfig' {
                        'url'('https://github.com/ZS-Omkar/frontend.git')
                    }
                }
                'branches' {
                    'hudson.plugin.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}

pipelineJob('CI-Pipelines/users') {
    configure { flowdefinition ->
        flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugin.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugin.git.UserRemoteConfig' {
                        'url'('https://github.com/ZS-Omkar/users.git')
                    }
                }
                'branches' {
                    'hudson.plugin.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}

pipelineJob('CI-Pipelines/login') {
    configure { flowdefinition ->
        flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugin.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugin.git.UserRemoteConfig' {
                        'url'('https://github.com/ZS-Omkar/login.git')
                    }
                }
                'branches' {
                    'hudson.plugin.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}

pipelineJob('CI-Pipelines/todo') {
    configure { flowdefinition ->
        flowdefinition << delegate. 'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
            'scm'(class:'hudson.plugin.git.GitSCM',plugin:'git') {
                'userRemoteConfigs' {
                    'hudson.plugin.git.UserRemoteConfig' {
                        'url'('https://github.com/ZS-Omkar/todo.git')
                    }
                }
                'branches' {
                    'hudson.plugin.git.BranchSpec' {
                        'name'('*/main')
                    }
                }
            }
            'scriptPath'('Jenkinsfile')
            'lightweight'(true)
        }
    }
}