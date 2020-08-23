pipeline
{
	agent any
		tools
		{
			jdk 'Java'
			maven 'Maven3'
		}
		options
	{
		timestamps()
		
		timeout(time: 1, unit: 'HOURS')
		
		skipDefaultCheckout()
		
		disableConcurrentBuilds()
	}
	
	stages
	{
	
		stage('checkout')
			{
				
				steps
				{
					echo "build in dev branch - 1"
					checkout scm
				}
			}
			
			stage('Build')
			{
				steps
				{
					echo "build in dev branch - 2"
					bat "mvn install"
				}
			}
			
			stage('Unit Testing')
			{
				steps
				{
					bat "mvn test"
				}
			}
			
			stage('Upload to Artifactory')
			{
				steps
				{
					rtMavenDeployer(
						id: 'deployer',
						serverId: 'divesh212@Artifactory',
						releaseRepo: 'divesh212-nagpDemo',
						snapshotRepo: 'divesh212-nagpDemo'
					)
					rtMavenRun(
						pom: 'pom.xml',
						goals: 'clean install',
						deployerId: 'deployer'
					)
					rtPublishBuildInfo(
						serverId: 'divesh212@Artifactory'
					)
				}
			}
			
			stage('Docker Image')
			{
				steps
				{
					bat "docker build -t divesh212/order-service3:build${BUILD_NUMBER} --no-cache -f Dockerfile ."
				}
			}
			
			stage('Docker Push')
			{
				steps
				{
					bat "docker push divesh212/order-service3:build${BUILD_NUMBER}"
				}
			}
			
			stage('Stop running Container')
			{
				steps
				{
					bat '''
					FOR /F "tokens=* USEBACKQ" %%F IN (`docker ps -qf name^=devops`) DO (
						SET ContainerID=%%F
					)
					IF NOT [%ContainerID%] = [] (
						echo "Stopping running container %ContainerID%"
						docker stop %ContainerID%
						docker rm -f %ContainerID%
					) ELSE ( echo "No existing container running")
				'''
				}
			}
			
			stage('Docker Deployment')
			{
				steps
				{
					bat "docker run --name devops-order-service -d -p 8092:8092 divesh212/order-service3:build${BUILD_NUMBER}"
				}
			}
	}
	
	post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
