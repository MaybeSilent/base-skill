#! /bin/bash

for file in `ls [0-9]*.java | grep -v '[0-9].java'`;
do	
	mv $file `echo $file | sed 's/\([0-9]*\).*/\1.java/g'`; 	
done
