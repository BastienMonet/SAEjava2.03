javac -d bin -cp lib/junit-4.13.2.jar:lib/hamcrest-2.2.jar  src/test/*.java

java -cp bin:lib/hamcrest-2.2.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore AppTest