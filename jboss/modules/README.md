To add a module: 
* Add a directory tree named after the "group" of your module
* Add to the innermost directory, yet another directory named "main"
* Finally, add in the "main" directory :
** your jars
** a file named "module.xml" (see below)

Here is a sample directory layout for several useful modules:
	.
	├── com
	│   ├── mysql
	│   │   └── main
	│   │       ├── module.xml
	│   │       └── mysql-connector-java-5.1.22.jar
	│   └── oracle
	│       └── main
	│           ├── module.xml
	│           └── ojdbc6-11.2.0.4.jar
	└── org
	    └── bouncycastle
	        └── main
	            ├── bcmail-jdk15on-1.50.jar
	            ├── bcpkix-jdk15on-1.50.jar
	            ├── bcprov-jdk15on-1.50.jar
	            └── module.xml
 
Here is a sample "module.xml" file. It is the one defined for the MySQL connector shown above.

	<?xml version="1.0" encoding="UTF-8"?>
	<module xmlns="urn:jboss:module:1.0" name="com.mysql">
	    <resources>
	    <resource-root path="mysql-connector-java-5.1.22.jar"/>
	    </resources>
	    <dependencies>
	    <module name="javax.api"/>
	    </dependencies>
	</module>

 
 