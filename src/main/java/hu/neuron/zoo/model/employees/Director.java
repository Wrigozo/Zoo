package hu.neuron.zoo.model.employees;

import hu.neuron.zoo.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Director extends Employee {

    private Director() {
    }

    public Director(String lName, String fName, LocalDate bDate, Gender g) {

        super(lName, fName, bDate, g);
    }
}
