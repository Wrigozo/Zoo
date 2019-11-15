package hu.neuron.zoo.model.employee;

import hu.neuron.zoo.model.enumsofzoo.Gender;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
public abstract class Employee implements Serializable {

    protected String lastName;

    protected String firstName;

    protected LocalDate birthDate;

    protected Gender gender;

    public static Map<Employee, Map> storedWorks = new HashMap<Employee, Map>();

    public Employee(){

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
     * @return a {@code String} value equals to the concatenation of the firstname and lastname.
     */
    public String getName() {
        return firstName + " " + lastName;
    }

}
