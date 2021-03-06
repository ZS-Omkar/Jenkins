def nexus(COMPONENT) {
    get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
    def get_branch_exec=sh(returnStdout: true, script: get_branch)
    def FILENAME=COMPONENT+'-'+get_branch_exec+'.zip'


    command = "curl -f -v -u admin:Omkar@123 --upload-file ${FILENAME} http://${NEXUS_IP}:8081/repository/${COMPONENT}/${FILENAME}"
   def execute_state=sh(returnStdout: true, script: command)
}

def make_artifacts(APP_TYPE, COMPONENT){
    get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
    def get_branch_exec=sh(returnStdout: true, script: get_branch)
    def FILENAME=COMPONENT+'-'+get_branch_exec+'.zip'
    if(APP_TYPE == "NGINX"){
        command = "echo ${COMPONENT} && zip -r ${FILENAME} ."
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    } else if(APP_TYPE == "GO") {
        command = "zip -r ${FILENAME} ."
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    } else if(APP_TYPE == "MAVEN"){
        command = "cp target/*.jar ${COMPONENT}.jar && zip -r ${FILENAME} ${COMPONENT}.jar ."
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    } else if(APP_TYPE == "NODEJS"){
        command = "zip -r ${FILENAME} node_modules server.js ."
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    }
}


def code_build(APP_TYPE, COMPONENT){
    if(APP_TYPE == "NGINX"){
        command = "npm install && npm run build"
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    } else if(APP_TYPE == "GO"){
        command = "go get && go build"
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    } else if(APP_TYPE == "MAVEN"){
        command = "mvn clean compile package"
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    } else if(APP_TYPE == "NODEJS"){
        command = "npm install"
        def execute_com=sh(returnStdout: true, script: command)
        print execute_com
    }
}