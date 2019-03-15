#!/usr/bin/env bash
npm run clean
npm run build
mv dist/index.html dist/index.ssr.html
echo "build for production success."