## mo_tester

### Requirements

- Java 8+ SDK
- maven
    - download : https://maven.apache.org/download.cgi
    - install : https://maven.apache.org/install.html
- library path
    - Windows : add lib_native directory to system environment PATH
    - Mac : add lib_native path to LD_LIBRARY_PATH
    - Others (Unix like) : add lib_native path (system dependent)
- change certificate
    - change certificates in certs directory (issued for your server)

### Compile

```
mvn compile
```

### Package

```
mvn package
```

### Run

```
java -jar mo_tester-1.0-jar-with-dependencies.jar HOST_NAME MO_PORT TLS_PORT
ex) java -jar mo_tester-1.0-jar-with-dependencies.jar 192.168.21.225 8080 18443
```