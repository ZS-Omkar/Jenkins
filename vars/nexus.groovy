def nexus() {
    command = "curl -f -v -u admin:Omkar@123 --upload-file /home/ubuntu/workspace/${COMPONENT}.zip http://${args.NEXUS_IP}:8081/repository/${COMPONENT}/${COMPONENT}.zip"
    def execute_state=sh(returnStdout: true, script: command)
}
