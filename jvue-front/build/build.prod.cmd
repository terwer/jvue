npm run clean &&^
npm run build &&^
move %cd%\dist\index.html %cd%\dist\index.ssr.html &&^
%cd%\build\cpm.cmd &&^
echo "build for production success."