build_folder_path="CMR-Workspace/01_Builds/"

test_folder_path='CMR-Workspace/02_Fastrack/'

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
for (i in Build_list){
     
    freeStyleJob("$build_folder_path$i"){
        concurrentBuild()//Execute build concurrently
        description("Build [$i]")
        //displayName("CHANDRAMOHAN")<-- rename the 'dms_darwin' as "CHANDRAMOHAN"
       
        logRotator {
            numToKeep(5)//Keep specified number of builds
            daysToKeep(2)// keep specified no of days
            artifactNumToKeep(2)//keep specified num of artifacts 
            artifactDaysToKeep(2)// keep specified num of days
        }
       // disabled(true) <-- disabled the job will not run until job is reenabled
       // disabled(fasle)<-- enabled the job
    }
    
    
}

for (i in Test_list){
     
    freeStyleJob("$test_folder_path$i"){
      
        concurrentBuild()//Execute build concurrently
        //displayName("CHANDRAMOHAN")<-- rename the 'fast_dms_darwin' as "CHANDRAMOHAN"
        description("Test [$i]")
       
        logRotator {
            numToKeep(5)//Keep specified number of builds
            daysToKeep(2)// keep specified no of days
            artifactNumToKeep(2)//keep specified num of artifacts 
            artifactDaysToKeep(2)// keep specified num of days
        }
       // disabled(true) <-- disabled the job will not run until job is reenabled
       // disabled(fasle)<-- enabled the job
    }
    
    
}
