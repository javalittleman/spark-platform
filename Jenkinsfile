node {
    checkout scm

    stage('Build') {
        ansiColor('vga'){
            docker.image('maven:3.3.3').inside('-v /data/maven/repo:/root/.m2 -v /etc/localtime:/etc/localtime -v $WORKSPACE/settings.xml:/usr/share/maven/conf/settings.xml -e TZ=Asia/Shanghai') {
                sh 'mvn -DskipTests clean package'
            }
        }
        // ansiColor('vga'){
        //     docker.image('gradle').inside('-v /etc/localtime:/etc/localtime -e TZ=Asia/Shanghai') {
        //         sh 'gradle clean build -x test'
        //     }
        // }
    }

    // stage('Build') {
    //     ansiColor('vga'){
    //         if (isUnix()) {
    //             echo "\033[5;30;42mLinux\033[0m system"
    //             sh 'mvn -Dmaven.test.failure.ignore -DskipTests clean package'
    //         } else {
    //             echo "\033[5;30;42mWindows\033[0m system"
    //             bat(/mvn -Dmaven.test.failure.ignore -DskipTests clean package/)
    //         }
    //     }
    // }



    stage('Deploy') {
        withEnv([
            'init=true',
            'DB_ENGINE=sqlite',
            'appDir=/data/dockerapp/spark-platform',
            'sshServer=192.168.108.81(prod)'
            ]) {


            if($init){
                sshPublisher(publishers: [
                        sshPublisherDesc(configName: '192.168.108.81(prod)', transfers: [
                        sshTransfer(cleanRemote: false, excludes: '',
                        execCommand:
                        '''
                            cd $appDir
                            printenv
                            docker-compose rm -svfa mysql redis nacos minio elk
                            sleep 60
                            rm data/ -rf
                            docker-compose up -d mysql redis nacos minio elk
                        ''',
                        execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                        remoteDirectory: '',
                        remoteDirectorySDF: false,
                        // removePrefix: 'target',
                        sourceFiles: '')
                    ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
                ])
            }
            sshPublisher(publishers: [
                    sshPublisherDesc(configName: '192.168.108.81(prod)', transfers: [
                    sshTransfer(cleanRemote: false, excludes: '',
                    execCommand:
                    '''
                        cd $appDir
                        printenv
                        docker-compose rm -svfa admin auth cms file flowable gateway quartz tx wx control biz
                    ''',
                    execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                    remoteDirectory: '',
                    remoteDirectorySDF: false,
                    // removePrefix: 'target',
                    sourceFiles: '')
                ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
            ])


            sshPublisher(publishers: [
                    sshPublisherDesc(configName: '192.168.108.81(prod)', transfers: [
                    sshTransfer(cleanRemote: false, excludes: '',
                    execCommand:
                    '''
                        cd $appDir
                        docker-compose up -d admin auth cms file flowable gateway quartz tx wx control biz
                    ''',
                    execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                    remoteDirectory: '/data/dockerapp/spark-platform',
                    remoteDirectorySDF: false,
                    // removePrefix: 'target',
                    sourceFiles: '**/*.jar,docker*.*,**/*.sql')
                ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
            ])



        }



        ansiColor('vga'){
            echo "构建成功！！！"
            echo "站点：\033[45mhttp://prod.y.com:8088/\033[0m"
        }
    }

    stage('Results') {
        archiveArtifacts '**/*.jar'
    }
}
