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
			
	}
	
	post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
