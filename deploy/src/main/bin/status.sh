#!/bin/bash

cd `dirname $0`
cd ..
BASE=`pwd`

PROCESS=`ps axfww | grep "com.gaohan.multipleprogram.Main" | grep " $BASE" | grep -v grep`
if [ ! -z "$PROCESS" ]; then
    echo started
else
    echo stoped
fi