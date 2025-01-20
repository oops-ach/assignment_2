package assignment2;

public class Student extends Person {
    private double gpa;

    public Student(int id, String name, String surname, double gpa) {
        super(id, name, surname);
        this.gpa= gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa ;
    }

    @Override
    public double getPaymentAmount() {
        return gpa > 2.67 ? 36660.0 : 0.0;
    }


    @Override
    public String getPosition() {
        return "";
    }

    @Override
    public String toString() {
        return "Student: " + super.toString() + " earns " + String.format("%.2f", getPaymentAmount()) + " tenge";
    }
}
