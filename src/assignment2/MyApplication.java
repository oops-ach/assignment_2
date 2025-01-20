package assignment2;

import java.util.ArrayList;
import java.util.Collections;

public class MyApplication {
    public static void main(String[] args) {
        ArrayList<Person> members = new ArrayList<>();

        members.add(new Student(3, "Ringo", "Starr", 0.0));
        members.add(new Student(4, "Paul", "McCartney", 3.5));

        members.add(new Employee(1, "John", "Lennon", "Engineer", 27045.78));
        members.add(new Employee(2, "Goerge", "Harrison", "Teacher", 50000.00));

        Collections.sort(members);
        Collections.reverse(members);

        printData(members);
    }

    public static void printData(ArrayList<Person> members){
        for(Payable member : members){
            System.out.println(member.toString() + " earns " + String.format("%.2f", member.getPaymentAmount()));
        }
    }
}
