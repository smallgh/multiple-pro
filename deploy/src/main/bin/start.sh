#!/bin/bash

cd `dirname $0`
cd ..
DEPLOY_DIR=`pwd`
CONF_DIR=$DEPLOY_DIR/conf
STDOUT_FILE=$DEPLOY_DIR/stdout.log

LIB_DIR=$DEPLOY_DIR/lib
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`

java -classpath $CONF_DIR/deamon.properties:$LIB_JARS com.gaohan.multipleprogram.Main > $STDOUT_FILE 2>&1 &

while [ -z "$PROCESS" ]; do
    sleep 2
    PROCESS=`ps axfww | grep "com.gaohan.multipleprogram.Main" | grep " $DEPLOY_DIR" | grep -v grep`
done

echo "OK!"
PIDS=`ps  --no-heading -C java -f --width 1000 | grep "$DEPLOY_DIR" | awk '{print $2}'`
echo "PID: $PIDS"


