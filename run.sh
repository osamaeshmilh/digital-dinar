#!/bin/bash

# Pull the latest changes
git pull

# Build the project using Maven
./mvnw package -Pprod -DskipTests

# Find the JAR file in the target directory
jar_file=$(find target -name "*.jar" -type f)

# Run the JAR file
java -jar "$jar_file"
