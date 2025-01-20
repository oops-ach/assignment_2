package assignment2;

public abstract class Person implements Payable, Comparable<Person> {
    private final int id;
    private String name;
    private String surname;

    public Person(int id){
        this.id = id;
    }

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getSurname() {
        return surname;
    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }

    public abstract String getPosition();

    @Override
    public String toString(){
        return id +". " + name + " " + surname;
    }

    @Override
    public int compareTo(Person otherPerson) {
        return Double.compare(otherPerson.getPaymentAmount(), this.getPaymentAmount());
    }

}
