#!/usr/bin/env bash
set -e
set -o pipefail

echo "Running as $GIT_USERNAME"

git config --global user.email "<>"
git config --global user.name "Booternetes CI Bot"

START=$(cd `dirname $0`/../.. && pwd )
echo starting at $START
RC=$HOME/Desktop/release_clone
RCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git
rm -rf $RC && mkdir -p $RC || echo "couldn't create the clone directory"
git clone $RCURL $RC
cd $RC && git rm -rf . && git commit -am au\ revoir && git checkout -b work
cd $START && git push  $RC main:release --force
cd $RC && git checkout release &&  git push origin release --force
echo "Pushed the code to the release repository."
