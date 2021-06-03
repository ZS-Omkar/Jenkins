def call(Map params = [:]) {

    def args = [
            NEXUS_IP: '172.31.4.7',
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
                stage('Download Dependencies') {
                    when {
                        environment name: 'COMPONENT', value: 'frontend'
                    }

                    steps {
                        sh '''
          npm install
        '''
                    }
                }
                stage('Making build') {
                    steps {
                        sh '''
                      npm run build
                    '''
                    }
                }
                stage('Prepare Artifacts') {
                    steps {
                        sh '''
                        zip -r ${COMPONENT}.zip *
                    '''
                    }
                }
                stage('Upload Artifacts') {
                    steps {
                        sh '''
                    curl -v -u admin:Omkar@123 --upload-file /home/ubuntu/workspace/${COMPONENT}.zip http://${args.NEXUS_IP}:8081/repository/${COMPONENT}/${COMPONENT}.zip
                    '''
                    }
                }
            }

        }

    }

}