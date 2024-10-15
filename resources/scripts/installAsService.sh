#!/bin/bash

# Variables.
package_manager="none";
exists_git=false;
exists_maven=false;
exists_java_17=false;


function write_file() {
    echo "[Unit]
Description=Task Manager API Daemon.
After=network.target

[Service]
ExecStart=java -jar /task-manager-app/task-manager-start.sh
Restart=on-failure

[Install]
WantedBy=multi-user.target" > /lib/systemd/system/task-manager.service
}

function move_jars() {
  #Crea una carpeta en el root, mueve los 3 jars

  if [ ! -d /task-manager-app ]
  then
  sudo mkdir /task-manager-app
  fi

  cp -r ../task-manager-application/target/task-manager-application-1.0-SNAPSHOT.jar /task-manager-app/task-manager-application.jar
  cp -r ../task-manager-infrastructure/target/task-manager-infrastructure-1.0-SNAPSHOT.jar /task-manager-app/task-manager-infrastructure.jar
  cp -r ../task-manager-domain/target/task-manager-domain-1.0-SNAPSHOT.jar /task-manager-app/task-manager-domain.jar

}

function install_dependencies() {
    if [ $exists_git == false ]; then
        if [ $package_manager == "aptitude" ]; then
            sudo apt-get install git
        elif [ $package_manager == "yum" ]; then
            sudo yum install git
        elif [ $package_manager == "pacman" ]; then
            sudo pacman -S git
        elif [ $package_manager == "dnf" ]; then
            sudo dnf install git
        elif [ $package_manager == "zypper" ]; then
            sudo zypper install git
        elif [ $package_manager == "yay" ]; then
            yay -S git
        fi
    fi

    if [ $exists_maven == false ]; then
        if [ $package_manager == "aptitude" ]; then
            sudo apt-get install maven
        elif [ $package_manager == "yum" ]; then
            sudo yum install maven
        elif [ $package_manager == "pacman" ]; then
            sudo pacman -S maven
        elif [ $package_manager == "dnf" ]; then
            sudo dnf install maven
        elif [ $package_manager == "zypper" ]; then
            sudo zypper install maven
        elif [ $package_manager == "yay" ]; then
            yay -S maven
        fi
    fi

    if [ $exists_java_17 == false ]; then
        if [ $package_manager == "aptitude" ]; then
            sudo apt-get install openjdk-17-jdk
        elif [ $package_manager == "yum" ]; then
            sudo yum install openjdk-17-jdk
        elif [ $package_manager == "pacman" ]; then
            sudo pacman -S openjdk-17-jdk
        elif [ $package_manager == "dnf" ]; then
            sudo dnf install openjdk-17-jdk
        elif [ $package_manager == "zypper" ]; then
            sudo zypper install openjdk-17-jdk
        elif [ $package_manager == "yay" ]; then
            yay -S openjdk-17-jdk
        fi
    fi
}

function check_software() {
  [ -x "$(which $1)" ]
}

function get_linux_package_manager() {
    if check_software apt-get ; then package_manager="aptitude"
    elif check_software yum ; then package_manager="yum"
    elif check_software pacman ; then package_manager="pacman"
    elif check_software dnf ; then package_manager="dnf"
    elif check_software zypper ; then package_manager="zypper"
    elif check_software yay ; then package_manager="yay"
    else
        echo 'No package manager found!'
        exit 2
    fi
}

function check_dependencies() {
    if (check_software git); then
        exists_git=true
    elif (check_software maven); then
        exists_maven=true
    elif (check_software java); then
        exists_java_17=true
    fi
}



# First, get the package manager for the current OS.
get_linux_package_manager

