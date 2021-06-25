#!/usr/bin/env bash
set -e
set -o pipefail
echo $GIT_USERNAME

echo "Hello!"

START=$(cd `dirname $0`/../.. && pwd )
echo starting at $START
RC=$HOME/Desktop/release_clone
RCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git
rm -rf $RC && mkdir -p $RC || echo "couldn't create the clone directory"
git clone $RCURL $RC
cd $RC
pwd  && exit  0
git rm -rf .
git commit -am goodbye
#git branch -a
git pull --force $START  main

#
#git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
##  git push git@github.com:booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
#
