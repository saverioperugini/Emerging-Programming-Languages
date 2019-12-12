@ECHO off
SET argC=0
for %%x in (%*) do SET /A argC+=1

IF /I "%argC%" NEQ "3" (
    ECHO "Usage: run.cmd <num_tournaments> <lat,long> <max_distance>" 1>&2
    EXIT /B 1
)

TYPE NUL > ./public/tournaments.json

python ./src/Requests.py %1 %2 %3

yarn start