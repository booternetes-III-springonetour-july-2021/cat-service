#!/usr/bin/env bash
set -e
set -o pipefail

echo "Running as $GIT_USERNAME"

git config --global user.email "<>"
git config --global user.name "Booternetes CI Bot"

START=$(cd `dirname $0`/../.. && pwd )
RC=$HOME/Desktop/release_clone
RCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/${GITHUB_REPOSITORY}-release.git
BACKUP_GIT_CONFIG=$HOME/Desktop/backup_git_config/

function promote_code() {
  rm -rf $RC && mkdir -p $RC || echo "couldn't create the clone directory"
  git clone $RCURL $RC
  cd $RC
  mv .git $BACKUP_GIT_CONFIG
  rm -rf $RC
  cp -r $START $RC && cd $RC && rm -rf $RC/.git && git init  && mv $BACKUP_GIT_CONFIG .git && \
  rm -rf target && find . | xargs -I e git add e    && git commit -am "polish $RANDOM" && git checkout -b release && \
  git branch -a && git push $RCURL  release --force
}

mvn clean verify && promote_code || echo "couldn't build and promote the code!"
