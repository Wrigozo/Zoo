package hu.neuron.zoo.model.employee;

import hu.neuron.zoo.model.enumsofzoo.Gender;
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

    /**
     * Director cannot be rewarded.
     */
    @Override
    protected void printGivesReward() {
    }

}
