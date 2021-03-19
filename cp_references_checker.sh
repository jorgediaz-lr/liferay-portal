#!/bin/bash
export REFERENCES_CHECKER_HOME=/mnt/c/Liferay/Code/my_developments/references-checker
export REPO_HOME=$(git rev-parse --show-toplevel)
for i in $(ls -1 ${REFERENCES_CHECKER_HOME}/lib/*.jar |grep -v "references-checker-cmd" |grep -v "references-checker-portal"); do cp -v $i ${REPO_HOME}/lib/portal; done
cp -v ${REFERENCES_CHECKER_HOME}/references-checker/src/main/resources/configuration_*.yml ${REPO_HOME}/portal-impl/src