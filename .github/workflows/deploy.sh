#!/usr/bin/env bash
set -e
set -o pipefail

RELEASE_BRANCH=release
ROOT_DIR=$(cd $(dirname $0) && pwd)
PROJECT_ID=${GCLOUD_PROJECT:-pgtm-jlong}


function move_code_to_release() {
#git checkout $RELEASE_BRANCH
#$ git push <remote> <local_branch>:<remote_name>

git push git@github.com:booternetes-III-springonetour-july-2021/cat-service-release.git  main:release --force
}

# todo force these things to be chained one to the other
#mvn clean deploy
move_code_to_release
