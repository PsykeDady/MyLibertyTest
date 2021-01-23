#!/bin/bash

IoEasier_VERSION=0.5
LittleJonH_VERSION=0.1
QueryCraft_VERSION=0.8

if [ ! -d lib ]; then mkdir lib; fi


# Install IoEasier.jar
wget https://github.com/PsykeDady/IOEasier/releases/download/v"$IoEasier_VERSION"/IoEasier.jar -O IoEasier.jar || exit 255

mv IoEasier.jar lib || exit 255

mvn install:install-file -Dfile=lib/IoEasier.jar  -DgroupId=psykeco -DartifactId=ioeasier -Dversion="$IoEasier_VERSION" -Dpackaging=jar -DgeneratePom=true -X || exit 255

# Install LittleJonH.jar
wget https://github.com/PsykeDady/LittleJonH/releases/download/v"$LittleJonH_VERSION"/LittleJonH.jar -O LittleJonH.jar || exit 255

mv LittleJonH.jar lib || exit 255

mvn install:install-file -Dfile=lib/LittleJonH.jar  -DgroupId=psykeco -DartifactId=littlejonh -Dversion="$LittleJonH_VERSION" -Dpackaging=jar -DgeneratePom=true -X || exit 255

# Install QueryCraft

wget https://github.com/PsykeDady/QueryCraft/releases/download/v"$QueryCraft_VERSION"/QueryCraft.jar -O QueryCraft.jar || exit 255

mv QueryCraft.jar lib || exit 255

mvn install:install-file -Dfile=lib/QueryCraft.jar  -DgroupId=psykeco -DartifactId=querycraft -Dversion="$QueryCraft_VERSION" -Dpackaging=jar -DgeneratePom=true -X || exit 255
