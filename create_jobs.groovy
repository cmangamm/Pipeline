folder('Test2/Synamedia2')
l= ['buildjob1','buildjob2','testjob1','testjob2']

def createJob(i)
{
  freeStyleJob("Test2/Synamedia2/$i")
  {
  scm {
        github('cmangamm/Pipeline', 'master')
    }
  }
}

for (i in l)
{
println ("Job: $i")
createJob(i)
}
pipelineJob("Test2/Synamedia2/workflow"){
  def wf_script="""
pipeline{ 
  agent any 
  stages{ 
    stage('BuildStage'){
      steps{
             build "buildjob1" 
      }
    }
    stage ('TestStage'){
       steps{
              build "testjob1"
       }
     }
  }
}
"""
  definition {
    cps{
      script(wf_script)
      sandbox(true)
    }
  }
}
