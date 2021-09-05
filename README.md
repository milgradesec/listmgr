# listmgr

![CI](https://github.com/milgradesec/listmgr/workflows/CI/badge.svg)
[![CodeQL](https://github.com/milgradesec/listmgr/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/milgradesec/listmgr/actions/workflows/codeql-analysis.yml)
[![codecov](https://codecov.io/gh/milgradesec/listmgr/branch/main/graph/badge.svg?token=Vw9zR2Qfcg)](https://codecov.io/gh/milgradesec/listmgr)
![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/milgradesec/listmgr)
![License](https://img.shields.io/github/license/milgradesec/listmgr)

`listmgr` is a tool to generate a unified list of domains from different sources.

## Build

```shell
.\gradlew build
```

## Usage

Example configuration file:

```ini
##############
# Lists.conf #
##############

# Some ads and tracking lists
https://raw.githubusercontent.com/StevenBlack/hosts/master/hosts
https://raw.githubusercontent.com/bigdargon/hostsVN/master/hosts
https://raw.githubusercontent.com/anudeepND/blacklist/master/adservers.txt
https://s3.amazonaws.com/lists.disconnect.me/simple_ad.txt
https://raw.githubusercontent.com/AdAway/adaway.github.io/master/hosts.txt
```

Run `listmgr`:

```shell
java -jar listmgr.jar --config lists.conf --output blocklist.list
```
