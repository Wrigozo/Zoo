package hu.neuron.zoo.model.employee;

import hu.neuron.zoo.model.enumsofzoo.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Director extends Employee {

    public Director(String lName, String fName, LocalDate bDate, Gender g) {
        lastName = lName;

        firstName = fName;

        birthDate = bDate;

        gender = g;
    }

}
