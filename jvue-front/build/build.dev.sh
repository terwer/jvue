#!/usr/bin/env bash
pwd
npm run clean
npm run dev-build
mv dist/index.html dist/index.ssr.html
echo "build for development success."