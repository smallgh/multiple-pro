#!/bin/bash

cd `dirname $0`
cd ..
BASE=`pwd`

ps axfww | grep "com.gaohan.multipleprogram.Main" | grep " $BASE" | grep -v grep | awk '{print $1}' | xargs kill -9