#CostShare

##1 Requirements

We have used the following software in our project, if you miss any of it just follow the instructions below. Or you can skip till step 4 and use the provided binaries.

###1.1 JDK

If you haven't installed a JDK yet we suggest to install the [JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

###1.2 Yeoman

To build the webapp you need Yeoman. You can get it [here](http://yeoman.io/).

###1.3 SASS

The webapp stylesheets are written in sass in combination with compass. So you probably have to [install](http://compass-style.org/install/) compass as well.  


##2 Usage

###2.1 Build

To build the project you can run `sbt assembly` from the root directory of the project. You can find the JAR in `target/scala-2.11/`.

###2.2 Parse SCSS and transform it to prettyprinted SCSS/CSS

`java -jar ass02.jar scssFile [--pretty scssOutputFilename] [--css cssOutputFilename]`


	scssFile:
 		relative or absolute path to scss file

 	scssOutputFilename:
 		relative or absolute path for prettyprinted scss file
  		default: ./pretty.scss

 	cssOutputFilename:
 		relative or absolute path for prettyprinted css file
  		default: ./pretty.css
 		
You can also use sbt to run the transformation: 

`sbt run scssFile [scssOutputFilename] [cssOutputFilename]`

##3 Current Status

We only covered the following scss [basics](http://sass-lang.com/guide):

- Variables
- Nesting
- Import
- Mixins
- Operators

[@rules](https://developer.mozilla.org/de/docs/Web/CSS/At-rule) (such as @charset, @font-face...) and line/block comments are not covered yet.



