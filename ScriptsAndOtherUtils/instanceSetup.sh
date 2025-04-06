#!/bin/bash
echo 'alias time="/usr/bin/time -f %S"' >> ~/.bashrc
source ~/.bashrc
sudo apt install inotify-tools
chmod +x time.txt
chmod +x executorScript.sh