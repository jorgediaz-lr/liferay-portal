#!/bin/bash
export REPO_HOME=$(git rev-parse --show-toplevel)
git add ${REPO_HOME}/lib/portal/*.jar
git add ${REPO_HOME}/portal-impl/src/*.yml