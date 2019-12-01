package hu.neuron.zoo.model.employees;

import hu.neuron.zoo.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public abstract class Employee implements Serializable {

    protected String lastName;

    protected String firstName;

    protected LocalDate birthDate;

    protected Gender gender;

    /**
     * Represents the first workday of employee.
     */
    protected LocalDate startWorkingDate;

    /**
     * Stores the {@code Employee}, the the first workday, and the task in a map.
     */
    @Getter
    public static Map<Employee, Map> storedWorks = new HashMap<Employee, Map>();

    public Employee() {
    }

    public Employee(String lName, String fName, LocalDate bDate, Gender g) {

        lastName = lName;

        firstName = fName;

        birthDate = bDate;

        gender = g;
    }

    /**
     * Gives the whole name of the Employee.
     *
     * @return a {@code String} value equals to the concatenation of the {@code #firstname} and {@code #lastname}.
     */
    public String getName() {
        return firstName + " " + lastName;
    }

}
