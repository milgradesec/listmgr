# list-manager

![CI](https://github.com/milgradesec/list-manager/workflows/CI/badge.svg)
[![Coverage](https://codecov.io/gh/milgradesec/list-manager/branch/master/graph/badge.svg)](https://codecov.io/gh/milgradesec/list-manager)
![License](https://img.shields.io/github/license/milgradesec/list-manager)

A tool to generate a unified list of domains from different sources.

## Build

    .\gradlew clean
    .\gradlew build

## Usage

    java -jar list-manager.jar --config lists.conf --output blocklist.list
