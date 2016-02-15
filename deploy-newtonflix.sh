#!/bin/bash
~/projects/t8/bin/shutdown.sh
echo $JAVA_HOME
echo $JAVA_HOME
echo $JAVA_HOME
echo $JAVA_HOME
echo $JAVA_HOME
echo $JAVA_HOME
echo $JAVA_HOME
pwd
pwd
date
date

cd ~/projects/t8/
rm -rf logs/*; rm -rf work/Catalina/localhost/new*; rm -rf webapps/newton*

#ls -al /Users/mjmc/projects/newton-flix-exercise/newton-flix/target/newtonflix-2.0.0-SNAPSHOT.war
#cp /Users/mjmc/projects/newton-flix-exercise/newton-flix/target/newtonflix-2.0.0-SNAPSHOT.war ~/projects/t8/webapps/newtonflix.war

ls -al /Users/mjmc/projects/newton-flix-exercise/newton-flix/build/libs/newtonflix-2.0.0-SNAPSHOT.war
cp /Users/mjmc/projects/newton-flix-exercise/newton-flix/build/libs/newtonflix-2.0.0-SNAPSHOT.war ~/projects/t8/webapps/newtonflix.war
#ls -al /Users/mjmc/projects/newton-flix-exercise/tmp/newton-flix/build/libs/newtonflix-2.0.0-SNAPSHOT.war
#cp /Users/mjmc/projects/newton-flix-exercise/tmp/newton-flix/build/libs/newtonflix-2.0.0-SNAPSHOT.war ~/projects/t8/webapps/newtonflix.war



ls -al ~/projects/t8/webapps/

~/projects/t8/bin/startup.sh

sleep 2

tail -f ~/projects/t8/logs/catalina.out
