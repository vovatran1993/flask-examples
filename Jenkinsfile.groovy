node {
properties([parameters([string(defaultValue: '127.0.0.1', description: 'Please give to build IP', name: 'IP', trim: true)])])
   
    stage("Install git"){
        sh "ssh    ec2-user@${IP}   sudo yum install git python-pip -y"
    }
    stage("Clone a repo"){
        git 'git@github.com:vovatran1993/flask-examples.git'
    }
    stage("Copy files"){
        sh "scp  -r * ec2-user@${IP}:/tmp/"
    }
    stage("Install requirements"){
        sh "sudo ssh ec2-user@${IP}   sudo pip install -r /tmp/requirements.txt"
    }
    stage("Run App"){
        sh "ssh ec2-user@${IP}  python  /tmp/01-hello-world/hello.py"
    }

}