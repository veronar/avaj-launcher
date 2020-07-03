echo "Loading all .java files into source.txt"
find . -name "*.java" > source.txt
echo "Done"
echo "Compiling all source files"
javac -sourcepath @source.txt
echo "Done"
echo "Run clean.sh to delete all .class files & source.txt"