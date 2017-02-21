cd O:\Program\netty-tutorial\protobuf\src\main\resources
set OUT=../java

protoc.exe ./*.proto --java_out=%OUT%

pause