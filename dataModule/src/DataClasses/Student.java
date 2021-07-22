package DataClasses;

import java.io.Serializable;
import java.util.Objects;

public class Student extends Human implements Serializable {
    private String specialization;
    private Integer groupNumber;

    public Student(String firstName, String lastName, Integer age, String specialization, Integer groupNumber) {
        super(firstName, lastName, age);
        this.specialization = specialization;
        this.groupNumber = groupNumber;
    }

    public Student() {
        super();
        this.specialization = null;
        this.groupNumber = null;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return "Student {" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", age=" + this.getAge() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", groupNumber=" + groupNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getSpecialization().equals(student.getSpecialization())
                && getGroupNumber().equals(student.getGroupNumber())
                && getFirstName().equals(student.getFirstName())
                && getLastName().equals(student.getLastName())
                && getAge().equals(student.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecialization(), getGroupNumber());
    }
}
