package hu.neuron.zoo.employee;

import hu.neuron.zoo.enumsofzoo.Gender;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public abstract class Employee implements Serializable {

    protected String lastName;

    protected String firstName;

    protected LocalDate birthDate;

    protected Gender gender;


    public String getName() {
        return firstName + " " + lastName;
    }

}
