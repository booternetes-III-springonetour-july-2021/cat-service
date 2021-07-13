#!/usr/bin/env bash
set -e
set -o pipefail

echo "Running as $GIT_USERNAME"

sudo snap install yq

git config --global user.email "<>"
git config --global user.name "Booternetes CI Bot"

ROC=$HOME/Desktop/release_ops_clone
ROCURL=https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/${GIT_USERNAME}/cat-service-release-ops.git

function update_tag() {
  rm -rf $ROC && mkdir -p $ROC || echo "couldn't create the clone directory"
  git clone $ROCURL $ROC
  cd $ROC

  # Get most recent tag from registry
  TAG=$(skopeo list-tags docker://${REGISTRY_HOST}/cat-service --creds $REGISTRY_USERNAME:$REGISTRY_PASSWORD \
  | jq 'del(.Tags[] | select(. == "latest"))' \
  | jq -r '.Tags |sort | reverse | .[0]')
  
  if [ "$TAG" == "" ]
  then
    echo "error: tag not found"
    return 1
  fi
  echo "newest tag: $TAG"

  # Get current tag from file
  CURRENT_TAG=$(yq e '.images[0].newTag' manifests/base/app/kustomization.yaml)
  echo "current tag: $CURRENT_TAG"

  # Check if the newest tag matches the current tag in file
  # If it has changed, update file
  # Otherwise error
  if [ "$TAG" == "$CURRENT_TAG" ]
  then 
    echo "error: tag has not changed"
    return 1
  else
    echo "updating tag $CURRENT_TAG to $TAG"
    sed -i "s/newTag.*/newTag: $TAG/" manifests/base/app/kustomization.yaml
    git add .
    git commit -am "tag $TAG"
    git push
  fi
}

update_tag
