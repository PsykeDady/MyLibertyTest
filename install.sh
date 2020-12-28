#!/bin/bash

IoEasier_VERSION=0.4

if [ ! -d lib ]; then mkdir lib; fi



wget https://github.com/PsykeDady/IOEasier/releases/download/"$IoEasier_VERSION"/IoEasier.jar

mv IoEasier.jar lib

mvn install:install-file -Dfile=lib/IoEasier.jar  -DgroupId=psykeco -DartifactId=ioeasier -Dversion="$IoEasier_VERSION" -Dpackaging=jar -DgeneratePom=true -X



