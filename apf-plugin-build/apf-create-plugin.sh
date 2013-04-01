#!bin/sh
# input: name of interface: com.example.plugin.ifs 
# create android lib project. 
android create lib-project $

# create android application project implementing the interface
android create project 

android update project 


# update build.xml for plugin and its implementation


# create host example. 