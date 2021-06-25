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
BACKUP_GIT_CONFIG=$HOME/Desktop/backup_git_config/
rm -rf $RC && mkdir -p $RC || echo "couldn't create the clone directory"
git clone $RCURL $RC
cd $RC
#git commit -am 'up ' # && git push --force ## this works!
mv .git $BACKUP_GIT_CONFIG
rm -rf $RC && mkdir -p $RC
cd $RC && git init
mv $BACKUP_GIT_CONFIG $RC/.git
cd $RC &&  git config --global init.defaultBranch release && git branch -m release && git checkout -b work
cd $START && rm -rf .git && cp -r  $START/* $RC/ && cd $RC && git add * && git commit -am sync && git push
#
#rm -rf $BACKUP_GIT_CONFIG || echo "couldn't delete backup .git config directory.."
#mkdir -p  $BACKUP_GIT_CONFIG &&  rm -rf $BACKUP_GIT_CONFIG
#ls -la $RC/.git
#cp -r $RC/.git/  $BACKUP_GIT_CONFIG
#rm -rf $RC
#mkdir -p $RC/
#cp -r $BACKUP_GIT_CONFIG  $RC/.git
#cd $RC  && git checkout -b work
#cd $START && git push $RC main:release --force
#
##mv $BACKUP_GIT_CONFIG .git
#
##  git rm -rf . && \
##  cp .git/config conf.bak && \
##  rm -rf .git && \
##  git init && \
##  mv conf.bak .git/config   && \
##  git commit -am ciao &&  \
##  git branch -m release && git checkout release
##
##cd $START && git push  $RC main:release --force
##cd $RC &&  git push --set-upstream origin release && git push origin release
#
##cd $RC && git rm -rf . && git commit -am au\ revoir && git checkout -b work
##cd $START && git push  $RC main:release --force
##cd $RC && git checkout release &&  git push origin release:release --force
#echo "Pushed the code to the release repository."