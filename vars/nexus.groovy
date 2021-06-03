def nexus() {
    command = "curl -f -v -u admin:Omkar@123 --upload-file ${COMPONENT}.zip http://172.31.4.7:8081/repository/${COMPONENT}/${COMPONENT}.zip"
    def execute_state=sh(returnStdout: true, script: command)
}
