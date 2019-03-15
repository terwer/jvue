#!/usr/bin/env bash
pwd
npm run clean
npm run dev-build
mv dist/index.html dist/index.ssr.html
build/cpm.sh
echo "build for development success."