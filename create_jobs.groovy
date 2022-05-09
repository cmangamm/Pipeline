sam = "cmr_dontDelete"
B_FOLDER = "$sam/Builds"
T_FOLDER = "$sam/Tests"
folder("${B_FOLDER}")
freeStyleJob("${B_FOLDER}/Build1"){
  steps{
    shell "echo 'I am Build1'"
  }
}
folder("${T_FOLDER}")
freeStyleJob("${T_FOLDER}/Test1"){
  steps{
    shell "echo 'I am test1'"
    shell "ls"
  }
}
pipelineJob("$sam/sam_workflow"){
  def wf_script="""
pipeline{ 
  agent any 
  stages{ 
    stage('BuildStage'){
      steps{
             build "Builds/Build1" 
      }
    }
    stage ('TestStage'){
       steps{
              build "Tests/Test1"
       }
     }
  }
}
"""
  definition {
    cps{
      script(wf_script)
      sandbox()
    }
  }
}
