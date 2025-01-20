package assignment2;

public class Employee extends Person{
    private String position;
    private double salary;


    public Employee(int id, String name, String surname, String position, double salary) {
        super(id, name, surname);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    @Override
    public double getPaymentAmount(){
        return salary;
    }

    @Override
    public String toString() {
        return "Employee: " + getId() + ". " + getName() + " " + getSurname() + " earns " + String.format("%.2f", getPaymentAmount()) + " tenge";
    }
}
