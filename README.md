# listmgr

![CI](https://github.com/milgradesec/listmgr/workflows/CI/badge.svg)
[![CodeQL](https://github.com/milgradesec/listmgr/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/milgradesec/listmgr/actions/workflows/codeql-analysis.yml)
[![codecov](https://codecov.io/gh/milgradesec/listmgr/branch/main/graph/badge.svg?token=Vw9zR2Qfcg)](https://codecov.io/gh/milgradesec/listmgr)
![License](https://img.shields.io/github/license/milgradesec/listmgr)

A tool to generate a unified list of domains from different sources.

## Build

    .\gradlew clean
    .\gradlew build

## Usage

    java -jar listmgr.jar --config lists.conf --output blocklist.list
