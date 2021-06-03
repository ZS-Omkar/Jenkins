def call() {

    pipeline {
        agent {
            label 'JAVA'
        }

        stages {

            stage('Download Dependencies') {
                steps {
                    sh '''
          npm install
        '''
                }
            }
            stage('Making build') {
                steps {
                    sh'''
                      npm run build
                    '''
                }
            }
            stage('Prepare Artifacts') {
                steps {
                    sh '''
                        zip -r frontend.zip *
                    '''
                }
            }
            stage('Upload Artifacts') {
                steps {
                    script {
                        nexus
                    }
                }
            }
        }
    }

}