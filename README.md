# list-manager

![CI](https://github.com/milgradesec/list-manager/workflows/CI/badge.svg)
[![CodeQL](https://github.com/milgradesec/list-manager/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/milgradesec/list-manager/actions/workflows/codeql-analysis.yml)
[![codecov](https://codecov.io/gh/milgradesec/list-manager/branch/main/graph/badge.svg?token=Vw9zR2Qfcg)](https://codecov.io/gh/milgradesec/list-manager)
![License](https://img.shields.io/github/license/milgradesec/list-manager)

A tool to generate a unified list of domains from different sources.

## Build

    .\gradlew clean
    .\gradlew build

## Usage

    java -jar listmgr.jar --config lists.conf --output blocklist.list
