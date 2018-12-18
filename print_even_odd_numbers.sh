#!/usr/bin/bash
echo "---------------Print Even and Odd numbers of given sequence------------"
for i in 0 1 2 3 4 5 6 7 8 9 10
do
    if [[ ($i%2 -eq 0) ]]
    then
        echo "Even Number = "$i
       
    else
        echo "Odd Number = "$i
    fi
done
