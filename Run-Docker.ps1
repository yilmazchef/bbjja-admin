if(Test-Item "docker-compose.yml"){
    Write-Host "App run started..."
    docker-compose.exe up -d
    Write-Host "App run completed!"
}