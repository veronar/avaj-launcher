package things;

public class Person {
    String firstName;
    String lastName;
    int age;


    public Person(String name, String surname, int age){
    this.firstName = name;
    this.lastName = surname;
    this.age = age;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}