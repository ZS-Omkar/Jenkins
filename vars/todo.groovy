def call(Map params = [:]) {

    def args = [
            NESUS_IP        :'172.31.4.7',
    ]
    args << params

    pipeline {
        agent {
            label "${args.SLAVE_LABEL}"
        }

        environment {
            COMPONENT = "${args.COMPONENT}"
            NEXUS_IP = "${args.NEXUS_IP}"
            PROJECT_NAME = "${args.PROJECT_NAME}"
            SLAVE_LABEL = "${args.SLAVE_LABEL}"
            APP_TYPE = "${args.APP_TYPE}"

            stages {
                stage('Building code & dependencies ') {
                    steps {
                        script {
                            build = new nexus()
                            build.code_build("${APP_TYPE}", "${COMPONENT}")
                        }
                    }
                }

                stage('Preparing Artifacts') {

                    steps {
                        script {
                            prepare = new nexus()
                            prepare.make_artifacts("${APP_TYPE}", "${COMPONENT}")
                        }
                        sh '''
                    ls
                    '''
                    }
                }


                stage('Uploading Artifacts') {
                    steps {
                        script {
                            upload = new nexus()
                            upload.nexus("${COMPONENT}")
                        }
                    }
                }
            }
        }
    }

