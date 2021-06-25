#!/usr/bin/env bash
set -e
set -o pipefail
echo $GIT_USERNAME
#git config user.name "Booternetes CI Bot"
#git config user.email "<>"
#mvn clean deploy && \

START=$(cd `dirname $0` && pwd )/../..
RC=$HOME/Desktop/release_clone
RCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git
rm -rf $RC
mkdir -p $RC
git clone $RCURL $RC
cd $RC
git rm -rf .
git pull $START  main

#
#git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
##  git push git@github.com:booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
#
