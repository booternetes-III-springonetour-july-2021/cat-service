#!/usr/bin/env bash
set -e
set -o pipefail

#mvn clean deploy && \
  git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force
#  git push git@github.com:booternetes-III-springonetour-july-2021/cat-service-release.git main:release --force

