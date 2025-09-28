mvn clean install -U
#!/bin/bash

echo "Running all algorithms and generating metrics.csv..."

# Compile and run Runner
mvn compile exec:java -Dexec.mainClass="com.assignment1.cli.Runner"

echo "Finished. Check Desktop/metrics.csv"
