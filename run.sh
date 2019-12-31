javac Main.java
#rm log
#java -ea main>log 2>&1
java -ea Main
rm *.class 
# rm  BinSearchTreeDir/*.class
# rm  LinkListDir/*.class
# rm  BinTreeDir/*.class
# rm  redBlackTree/*.class
 rm  common/*.class
 #rm  cutRod/*.class
#rm  fbnq/*.class
rm longestCommonSequence/*.class

#vim log
