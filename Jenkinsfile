node {
    checkout scm

    withEnv([
        "compile='false'",
        "init='true'"
    ]) {
        if(env.compile=='true'){
            sh "printenv"
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

        }

        stage('Deploy') {

            ansiColor('vga'){

                // 初始化
                if(env.init=='true'){
                    sh "初始化"
                    sshPublisher(publishers: [
                            sshPublisherDesc(configName: '192.168.108.81(prod)', transfers: [
                            sshTransfer(cleanRemote: false, excludes: '',
                            execCommand:
                            '''
                                cd /data/dockerapp/spark-platform
                                docker-compose rm -svfa mysql redis nacos minio elk
                                rm data/ -rf
                                docker-compose up -d mysql redis nacos minio elk
                                sleep 60
                            ''',
                            execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                            remoteDirectory: '',
                            remoteDirectorySDF: false,
                            // removePrefix: 'target',
                            sourceFiles: '')
                        ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
                    ])
                }

                // 停止应用，为传输打包文件做准备
                sh "停止应用，为传输打包文件做准备"
                sshPublisher(publishers: [
                        sshPublisherDesc(configName: '192.168.108.81(prod)', transfers: [
                        sshTransfer(cleanRemote: false, excludes: '',
                        execCommand:
                        '''
                            cd /data/dockerapp/spark-platform
                            docker-compose rm -svfa auth cms file flowable gateway quartz tx wx control biz
                        ''',
                        execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                        remoteDirectory: '',
                        remoteDirectorySDF: false,
                        // removePrefix: 'target',
                        sourceFiles: '')
                    ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
                ])

                // 传输打包文件并启动应用
                sh "传输打包文件并启动应用"
                sshPublisher(publishers: [
                        sshPublisherDesc(configName: '192.168.108.81(prod)', transfers: [
                        sshTransfer(cleanRemote: false, excludes: '',
                        execCommand:
                        '''
                            cd /data/dockerapp/spark-platform
                            docker-compose up -d auth cms file flowable gateway quartz tx wx control biz
                        ''',
                        execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+',
                        remoteDirectory: '/data/dockerapp/spark-platform',
                        remoteDirectorySDF: false,
                        // removePrefix: 'target',
                        sourceFiles: '**/*.jar,docker*.*,**/*.sql')
                    ], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)
                ])

                echo "构建成功！！！"
                echo "站点：\033[45mhttp://prod.y.com:8088/\033[0m"

            }

        }

        stage('Results') {
            archiveArtifacts '**/*.jar'
        }


    }





}
