if(Test-Item "docker-compose.yml"){
    Write-Host "App run started.." `
    mvn clean install `
    mvn spring-boot:run `
    Write-Host "App run completed!"
}