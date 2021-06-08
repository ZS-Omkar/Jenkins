def call(Map params = [:]) {

    def args = [
            NEXUS_IP: '172.31.4.7',
    ]
    args << params

    pipeline {
        agent {
            label "${args.SLAVE_LABEL}"
        }
        tools {
            go 'Go 1.11.4'
        }

        environment {
            COMPONENT    = "${args.COMPONENT}"
            NEXUS_IP     = "${args.NEXUS_IP}"
            PROJECT_NAME = "${args.PROJECT_NAME}"
            SLAVE_LABEL  = "${args.SLAVE_LABEL}"
            APP_TYPE     = "${args.APP_TYPE}"
            GO111MODULE  = 'on'
        }

            stages {
                stage('Build Code & Install Dependencies') {
                    steps {
                        script {
                            build = new nexus()
                            build.code_build("${APP_TYPE}", "${COMPONENT}")
                        }
                    }
                }
                stage('Prepare Artifacts') {
                    steps {
                        script {
                            prepare = new nexus()
                            prepare.make_artifacts("${APP_TYPE}", "${COMPONENT}")
                        }
                    }
                }
                stage('Upload Artifacts') {
                    steps {
                        script {
                            prepare = new nexus()
                            prepare.nexus(COMPONENT)
                        }
                    }
                }
            }
        }
    }