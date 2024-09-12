# Use the official Gitpod base image
FROM gitpod/workspace-full

# Install necessary packages
RUN sudo apt-get update && sudo apt-get install -y \
    openjdk-20-jdk

# Set Java 20 as the default Java version
RUN sudo update-alternatives --set java /usr/lib/jvm/java-20-openjdk-amd64/bin/java

# Install Maven
RUN sudo apt-get install -y maven
