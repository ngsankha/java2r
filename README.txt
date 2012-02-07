Java2R Version 1.0 - Readme
~~~~~~~~~~~~~~~~~~~~~~~~~~~

Contents
~~~~~~~~
1. Introduction
2. License
3. System Requirements
4. Latest Version
5. Installation
6. Documentation
7. Examples
8. Files
9. Distributing your Application
10. Building the Source Code
11. Contact Me & Support

1. Introduction
~~~~~~~~~~~~~~~
R is software for statistical computing. It has its own scripting
language. Its power and flexibility has led to the growth of its
popularity. This library Java2R is a port to R and can be used in
your Java programs to connect R and Java. As you can well imagine
then you can have rich interactive applications at the same time
use the power of R. You can use this to perform statistical
computations from R and used to prepare high quality graphs.

2. License
~~~~~~~~~~
This is a open-source free library licensed under GNU General Public
License. The full license text can be found in the COPYING.txt file.

3. System Requirements
~~~~~~~~~~~~~~~~~~~~~~
To use Java2R you should have
	* A recent version of the JDK (I have tested this with JDK 1.6.0)
	* Any operating system on which R binaries can run. 
	  (I have tested this with Microsoft Windows XP Service Pack 3)
	* A proper R installation (I have tested this with R 2.7.1)
	  You can get one from http://www.r-project.org/

4. Latest Version
~~~~~~~~~~~~~~~~~
The latest version can be obtained from https://github.com/sankha93/java2r

5. Installation
~~~~~~~~~~~~~~~
To use Java2R in your Java programs you need to add the "Java2R.jar"
file to your CLASSPATH. To know how to change your CLASSPATH
environment variable please consult your system documentation.
To do this in Windows execute the follwing command after going to the
Command Prompt.
	SET CLASSPATH=%CLASSPATH%;<path>
Replace <path> with the location of the Java2R.jar file.

You can also use it with javac and java by
	javac -cp <path to Java2R.jar> <Your source code files>
	java -cp <path to Java2R.jar> <Your class files>
	
To use it in your IDE please coonsult your IDE documentation on how to add
add Libraries to your projects.

6. Documentation
~~~~~~~~~~~~~~~~
The Java2R API Javadoc can be found in the docs folder. Please open the
index.html file in your browser to view it.

7. Examples
~~~~~~~~~~~
Examples for Java2R can be found in the examples folder. You need to
compile the source code files to run the examples.

To compile them use
	javac -cp <path to Java2R.jar> <Example source code files>
	
To run them do
	java -cp <path to Java2R.jar> <Example class files>

8.Files
~~~~~~~
The following files are included in the distribution
	README.TXT	This file
	COPYING.TXT	The full license text
	Java2R.jar	This the jar file of Java2R
	docs		This folder contains the library documentation
	source		The source code for this library

9. Distributing your Application
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
All applications that you build with the keyval library should distribute the
Java2R.jar file in order to run the application properly and it should have
a proper version of R installed for that system.

10. Building the Source Code
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
To build the source code you should have Ant (http://ant.apache.org)
installed or NetBeans IDE 6.5 installed. This was created in NetBeans
so you can open the source code as project in NetBeans directly.

To build it with ant make sure that the source folder is your current
directory. Then execute the following command
		ant

11. Contact Me & Support
~~~~~~~~~~~~~~~~~~~~~~~~
There may be bugs in this library. If you find any problem or have any
suggestion then kindly report them to me.

If possible you may rewrite some parts of the program and send them to me.

Website: https://github.com/sankha93/java2r
E-Mail: sankha93@gmail.com
