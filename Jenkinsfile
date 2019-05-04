/* Fasttrack workflow pipeline job to trigger build and test jobs */
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
Deploy_list=[
  
         'deploy_job'
]
stage('Build'){
    (Build_list).each{
        build "../01_Builds/${it}"
    }
}
stage('Test'){
    (Test_list).each{
        build "${it}"
    }
}
stage('Deploy'){
    (Deploy_list).each{
        build "../03_Deploy/${it}"
    }
}
