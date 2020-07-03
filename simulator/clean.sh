echo "Deleting all .class files."
rm -rf *.class
rm -rf ./interfaces/*.class
rm -rf ./vehicles/*.class
rm -rf ./weather/*.class
echo "Done."

echo "Deleting source.txt"
rm -rf source.txt
echo "Done. To remake -> ./run.sh"