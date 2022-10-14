@echo off
set/p input=The plaintext string to be encrypted: 
set/p password=Secret key: 
echo Encrypting......
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI ^
input=%input% password=%password% ^
algorithm=PBEWithMD5AndDES
pause