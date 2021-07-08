name: Automate_tests

env:
  ARTIFACTORY_USERNAME: ${{ secrets.ARTIFACTORY_USERNAME  }}
  ARTIFACTORY_PASSWORD: ${{ secrets.ARTIFACTORY_PASSWORD  }}
  GIT_USERNAME: ${{ secrets.GIT_USERNAME  }}
  GIT_PASSWORD: ${{ secrets.GIT_PASSWORD  }}

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
  repository_dispatch:
    types: cat-meow

  curl -H “Authorization: token ${GH_PAT}” —request POST —data ‘{“event_type”: “cat-meow”}’ https://api.github.com/repos/booternetes-III-springonetour-july-2021/cat-service/dispatches

jobs:
  build:
    runs-on: ubuntu-latest
    steps:

      - uses: actions/checkout@v2

      - uses: actions/cache@v1
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-maven-

      - name: Set up JDK 11
        uses: actions/setup-java@v1
        with:
          java-version: 11

      - name: Automate_tests
        run: |
          cd $GITHUB_WORKSPACE
          .github/workflows/automate_tests.sh
