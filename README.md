This is a solution to stream mocap data via internet by using Axis studio.

# Use case
There are two locations: location1 and location2. 
- The neuron hardware and software(Axis) is installed in location1. The mocap data (BVH format) captured in location1 can be streamed to location2. 
- At location2, such software as unity3D, Unreal with Neuron plugin are installed. By using neuron plugin the software can receive the mocap data in real time.

# Solution
Since location1 and location2 can't be connected directly, a server is needed to transfer the stream data between them. Both location1 and location2 need to connect to the server to upload and download data.

Below diagram shows the architecture and data flowing:

![image](https://note.youdao.com/yws/public/resource/0a2bb717f8a72a1ac060ee25d5f33347/xmlnote/E728654FF58E4B87B479AB85877DD0C5/84250)

As the diagraming show, the mocap data can be broadcasted to more than one locations.
 
The two components marked with yellow background in the diagram are the new added applications to stream the mocap data.
 1. Server: An application running in the cloud transfer the data from upclient(location1) to downclient(location2). Its IP address is knowed by apps running at location1 and location2.
 2. Upload App：A java application running at upclient side(location1). It has two functionalities:
    - Get the BVH data from Axis app via TCP protocol locally
    - Connect to Server and upload the BVH data to server

# Setup upclient
## Get pns-upclient app
pns-upclient app can be built from source code or download from release page.
### build steps
1. build app
```
gradlew.bat distZip
```
2. target file is: build\distributions\pns-upclient.zip

### download page
https://github.com/neuronmocap/pns-upclient/releases/tag/v0.1.0

## Run pns-upclient app
### precondition
- OS of local pc is windows
- PN Axis app is already installed in pc. 
- JDK1.8(java environment) or above is installed in local PC.

### steps
1. unzip the pns-upclient.zip
2. run PN Axis app and enable port 7001
3. pns-server is running in server
4. run the bat file with server ip as parameter
```
\path\to\pns-upclient.bat xxx.xxx.xxx.xxx
```
> xxx.xxx.xxx.xxx is the server ip on which pns-server is running
5. console log is as following:
```
connecting  27.8.1.137:9998
connecting  127.0.0.1:7001
2020/04/08 18:48:35,fps=0,size=1024
2020/04/08 18:48:36,fps=151,size=784
2020/04/08 18:48:37,fps=150,size=784
2020/04/08 18:48:38,fps=151,size=784
2020/04/08 18:48:39,fps=151,size=784
2020/04/08 18:48:40,fps=151,size=784
2020/04/08 18:48:41,fps=150,size=784
```
> The log is printed every one second. frames per second is 150 and each frame has 784 bytes.
