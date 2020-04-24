1. builid app,target file is: build\distributions\pnstream-upclient.zip
```
gradlew.bat distZip
```

2. unzip pnstream-server.zip and run
```
cd build\distributions
unzip pnstream-upclient.zip
pnstream-upclient\bin\pnstream-upclient.bat 127.0.0.1
```

> 127.0.0.1 can replaced with the real ip on which pnstream-server is running.

