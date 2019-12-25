javac main.java
#rm log
#java -ea main>log 2>&1
java -ea main
rm *.class 
# rm  BinSearchTreeDir/*.class
# rm  LinkListDir/*.class
# rm  BinTreeDir/*.class
rm  redBlackTree/*.class
rm  common/*.class
#vim log
