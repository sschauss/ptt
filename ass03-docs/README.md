#CostShare

##1 Requirements

We have used the following software in our project, if you miss any of it just follow the instructions below. Or you can skip till step 4 and use the provided binaries.

###1.1 JDK

If you haven't installed a JDK yet we suggest to install the [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

###1.2 Yeoman

To build the webapp you need Yeoman. You can get it [here](http://yeoman.io/).

###1.3 SASS

The webapp stylesheets are written in sass in combination with compass. So you probably have to [install](http://compass-style.org/install/) compass as well. 

###1.4 Maven

To build the server binaries we've used [maven](http://maven.apache.org/download.cgi). 

##2 Build

To build the project you can use the build script (in the root folder of the ptt project).

Alternative:

1. From the ass03-client folder run:
 	`bower install && npm install && grunt build`
2. Copy the contents of the ass03-client/dist folder to ass03-server/src/main/resources/assets
3. From the ass03-server folder run:
	`mvn install`

##3 Run

###3.1

Create file with .yml suffix.

	database:
  		# the name of your JDBC driver
  		driverClass: org.h2.Driver

  		# the username
  		user: costshare

  		# the password
  		password: 

  		# the JDBC URL
  		url: jdbc:h2:~/costshare
  		
###3.2

Run `java -jar [path to server.jar file] server [path to config.yml file]`




