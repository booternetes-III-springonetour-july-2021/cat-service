#!/usr/bin/env bash
set -e
set -o pipefail

echo "Running as $GIT_USERNAME"

sudo snap install yq

git config --global user.email "<>"
git config --global user.name "Booternetes CI Bot"

ROC=$HOME/Desktop/release_ops_clone
ROCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/${GIT_USERNAME}/cat-service-release-ops.git

rm -rf $ROC && mkdir -p $ROC || echo "couldn't create the clone directory"
git clone $ROCURL $ROC
cd $ROC

function update_tag() {
  for i in {1..20}
  do
    echo "attempt $i/20"

    # Get most recent tag from registry
    # Right now this assumes kpack didn't restart the build number
    TAG=$(skopeo list-tags docker://${REGISTRY_HOST}/cat-service --creds $REGISTRY_USERNAME:$REGISTRY_PASSWORD \
    | jq 'del(.Tags[] | select(. == "latest"))' \
    | jq -r '.Tags |sort | reverse | .[0]')

    if [ "$TAG" == "" ]
    then
      echo "tag not found"
      continue
    fi
    echo "newest tag:  $TAG"

    # Get current tag from file
    CURRENT_TAG=$(yq e '.images[0].newTag' manifests/base/app/kustomization.yaml)
    echo "current tag: $CURRENT_TAG"

    # Check if the newest tag matches the current tag in file
    # If it has changed, update file
    if [ "$TAG" == "$CURRENT_TAG" ]
    then
      echo "tag has not changed"
      sleep 1m
    else
      echo "updating tag $CURRENT_TAG to $TAG"
      sed -i "s/newTag.*/newTag: $TAG/" manifests/base/app/kustomization.yaml
      git add .
      git commit -am "tag $TAG"
      git push
      break
    fi
  done
}

update_tag || echo "couldn't update tag"
