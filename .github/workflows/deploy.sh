#!/usr/bin/env bash
set -e
set -o pipefail

export PROJECT_ID=${GCLOUD_PROJECT:-pgtm-jlong}
export ROOT_DIR=$(cd $(dirname $0) && pwd)

mvn clean deploy

git push git@github.com:booternetes-III-springonetour-july-2021/cat-service-release.git  --force
