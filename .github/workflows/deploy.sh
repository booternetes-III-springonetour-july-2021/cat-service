#!/usr/bin/env bash
set -e
set -o pipefail
echo $GIT_USERNAME


START=$(cd `dirname $0` && pwd )/../..
RC=$HOME/Desktop/release_clone
RCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git
rm -rf $RC && mkdir -p $RC || echo "couldn't create the clone directory"
git clone $RCURL $RC
cd $RC
git rm -rf .
git branch -a
git pull $START  main

#
#git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
##  git push git@github.com:booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
#
