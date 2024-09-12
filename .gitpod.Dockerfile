# Use the official Gitpod base image
FROM gitpod/workspace-full

# Install necessary packages
RUN sudo apt-get update && sudo apt-get install -y \
    openjdk-21-jdk \
    maven

# Set Java 21 as the default Java version
RUN sudo update-alternatives --set java /usr/lib/jvm/java-21-openjdk-amd64/bin/java
