if(Test-Item "docker-compose.yml"){
    Write-Host "Running on Docker..."
    docker-compose.exe up -d
}