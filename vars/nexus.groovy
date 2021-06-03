def nexus() {
    command = "curl -v -u admin:Omkar@123 --upload-file /home/ubuntu/workspace/frontend.zip http://172.31.4.7:8081/repository/frontend/frontend1.zip"
    def execute_state=sh(returnStdout: true, script: command)
}
