#!/bin/bash
REFERENCES_CHECKER_HOME=/mnt/c/Liferay/Code/my_developments/references-checker

ORIGINAL_WORKING_DIR=$(pwd)

cd $(git rev-parse --show-toplevel)

ln -s ${REFERENCES_CHECKER_HOME} references-checker 2> /dev/null

for i in $(ls -1 references-checker/lib/*.jar |grep -v "references-checker-cmd" |grep -v "references-checker-portal"); do cp -v $i lib/portal; done
cp -v references-checker/references-checker/src/main/resources/*.yml portal-impl/src

#update classpath
grep -v "path=\"references-checker" .classpath > .classpath_filtered
mv .classpath_filtered .classpath

NUMLINES=$(wc -l .classpath | tr -d " " |cut -d"." -f1 )
let NUMLINES=NUMLINES-2

head -n $NUMLINES .classpath > .classpath_new

for line in $(ls -1d references-checker/*/src/main/* |grep -v "references-checker-cmd" |grep -v "references-checker-portal"); do
	echo -e "\t<classpathentry kind=\"src\" path=\"$line\" output=\"bin/${line}\" />" >> .classpath_new
done
echo -e "\t<classpathentry kind=\"output\" path=\"bin/portal\" />" >> .classpath_new
echo "</classpath>" >> .classpath_new
mv .classpath_new .classpath

cd ${ORIGINAL_WORKING_DIR}