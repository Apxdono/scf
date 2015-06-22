#!/usr/bin/env bash
find ./src/main/javascript/js -name '*.js' -exec ng-annotate -ar {} -o {} \;
node r.js -o build.js