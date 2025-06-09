javac -d bin -cp lib/junit-4.13.2.jar:lib/hamcrest-2.2.jar  src/test/*.java

java -cp bin:lib/hamcrest-2.2.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore AppTest



GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY '4dameorc' WITH GRANT OPTION;


# Maven
mvn compile 
mvn exec:java -Dexec.mainClass="fr.saejava.ExecutableTest"

mvn exec:java -Dexec.mainClass="fr.saejava.InterfaceCLI"



mvn test
