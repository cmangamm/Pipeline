build_folder_path="CMR-Workspace/01_Builds/"
test_folder_path='CMR-Workspace/02_Fastrack/'
Notify_path='CMR-Workspace/03_Deploy/'

def runScript=readFileFromWorkspace('print_numbers.sh')

Build_list=[
  
         'dms_darwin',
         'dms_it_falcon2',
         'dms_it_mr',
         'dms_uk_falcon',
         'dms_uk_falcon_v2',
         'dms_uk_mr',
         'dms_uk_olympus',
         'dms_uk_titan',
         'dms_uk_xwing',

]

Test_list=[
  
         'fast_dms_darwin',
         'fast_dms_it_falcon2',
         'fast_dms_it_mr',
         'fast_dms_uk_falcon',
         'fast_dms_uk_falcon_v2',
         'fast_dms_uk_mr',
         'fast_dms_uk_olympus',
         'fast_dms_uk_titan',
         'fast_dms_uk_xwing',
  
]
Notify=[
  
         'Notified_Job'
]
for (i in Build_list){
     
    freeStyleJob("$build_folder_path$i"){
        
        description("Build [$i]")
        //displayName("CHANDRAMOHAN")<-- rename the 'dms_darwin' as "CHANDRAMOHAN"
       
        logRotator {
            numToKeep(5)//Keep specified number of builds
            daysToKeep(2)// keep specified no of days
            artifactNumToKeep(2)//keep specified num of artifacts 
            artifactDaysToKeep(2)// keep specified num of days
        }
        concurrentBuild()//Execute build concurrently
        scm {
            github('cmangamm/Pipeline')
        }
        triggers{
            githubPush() 
        }
        parameters{
            stringParam("Name1","ChandraMohan Reddy","This is first parrameter")
            stringParam("Name2","RaviKanth Reddy","This is second parrameter")
            stringParam("Name3","Neelakanta Reddy","This is third parrameter")
            stringParam("Name4","Hamsa Reddy","This is fourth parrameter")
            stringParam("Name5","Vidhurnath Reddy","This is fifth parrameter")
            stringParam("Name6","Sandhya Reddy","This is sixth parrameter")
            booleanParam("Trigger_Build",true,"uncheck to not to run build")
        }
        steps{
          
          //Creates First excute shell window
          shell("./print_even_odd_numbers.sh")//Run the script which was cloned,script in current working directory
       
          //Creates second execute shell window    
          shell(runScript)//Excute script commands in shell window
          
        }
        //customWorkspace("/var/lib/jenkins/workspace/CMR-Workspace")<-- sets the common workspace for all build jobs
        
       // disabled(true) <-- disabled the job will not run until job is reenabled
       // disabled(fasle)<-- enabled the job
      
       // Performing post build actions
      
       publishers{
           archiveArtifacts{
               pattern("*.groovy")
               pattern("*.sh")
           }
           extendedEmail{
               recipientList("chandu80006@gmail.com","cmangamm@cisco.com")
               defaultSubject("Release Notes for CMR's pipeline")
               defaultContent("<html><body><H1>Hello ChandraMohanReddy</H1></body></html>")
               contentType("text/html")
               triggers{
                 always{
                     sendTo {
                         recipientList() 
                     }
                 }
               }
           }
       }
    }
    
    
}

for (i in Test_list){
     
    freeStyleJob("$test_folder_path$i"){
      
        //displayName("CHANDRAMOHAN")<-- rename the 'fast_dms_darwin' as "CHANDRAMOHAN"
        description("Test [$i]")
       
        logRotator {
            numToKeep(5)//Keep specified number of builds
            daysToKeep(2)// keep specified no of days
            artifactNumToKeep(2)//keep specified num of artifacts 
            artifactDaysToKeep(2)// keep specified num of days
        }
        concurrentBuild()//Execute build concurrently
        scm {
            github('cmangamm/Pipeline')
        }
        triggers{
            githubPush() 
        }
        parameters{
            stringParam("Name1","ChandraMohan Reddy","This is first parrameter")
            stringParam("Name2","RaviKanth Reddy","This is second parrameter")
            stringParam("Name3","Neelakanta Reddy","This is third parrameter")
            stringParam("Name4","Hamsa Reddy","This is fourth parrameter")
            stringParam("Name5","Vidhurnath Reddy","This is fifth parrameter")
            stringParam("Name6","Sandhya Reddy","This is sixth parrameter")
            booleanParam("Trigger_Test",true,"uncheck to not to run Test")
        }
        steps{
          
          shell("./print_even_odd_numbers.sh")//Run the script which was cloned,script in current working directory
       
          shell(runScript)//Excute script commands in shell window
          
        }
        //customWorkspace("/var/lib/jenkins/workspace/CMR-Workspace")<-- sets the common workspace for all test jobs
        // disabled(true) <-- disabled the job will not run until job is reenabled
        // disabled(fasle)<-- enabled the job
              
       // Performing post build actions
       publishers{
           archiveArtifacts{
               pattern("*.groovy")
               pattern("*.sh")
           }
           extendedEmail{
               recipientList("chandu80006@gmail.com","cmangamm@cisco.com")
               defaultSubject("Release Notes for CMR's pipeline")
               defaultContent("<html><body><H1>Hello ChandraMohanReddy</H1></body></html>")
               contentType("text/html")
               triggers{
                 always{
                     sendTo {
                         recipientList() 
                     }
                 }
               }
           }
       }

    }
    
    
}

for (i in Notify){
  
  freeStyleJob("$Notify_path$i"){
    
    description("This job sent mail on any occassion to receiptents")
   
    logRotator{
      
        daysToKeep(2)
        numToKeep(5)
        artifactDaysToKeep(1)
        artifactNumToKeep(3)
        
    }
    
    scm{
        github('cmangamm/Pipeline')
    }
    triggers{
        githubPush()
    }
    steps{
      
        shell(runScript)
      
    }
    publishers{
        archiveArtifacts{
            pattern("*.groovy")
            pattern("*.sh")
        }
        extendedEmail{
            
          recipientList("chandu80006@gmail.com","cmangamm@cisco.com")
          defaultSubject("Release Notes from Notified Job")
          defaultContent("<html><body></H1>Hello ChandraMohanReddy Mangammgari</H1></body></html>")
          contentType("text/plain")
          triggers{
              always{
                  sendTo{               
                      recipientList()
                  }
              }
          }
        }
        
    }
                
  }
  
}
