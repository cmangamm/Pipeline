folder('Test2/Synamedia2')
l= ['buildjob1','buildjob2','testjob1','testjob2']

def createJob(i)
{
  freeStyleJob("Test2/Synamedia2/$i")
  {
  scm {
        github('jenkinsci/job-dsl-plugin', 'master')
    }
  }
}

for (i in l)
{
println ("Job: $i")
createJob(i)
}
