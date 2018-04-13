label('Node1')
stage("Build") {
    println "This Build stage"
}
stage("Test") {
    println "This is Test Stage"
}
stage("Deploy") {
    println "This is Deployment stage"
}
