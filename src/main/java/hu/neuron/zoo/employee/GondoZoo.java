package hu.neuron.zoo.employee;

import hu.neuron.zoo.enumsofzoo.Gender;
import hu.neuron.zoo.enumsofzoo.Species;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class GondoZoo extends Employee {

    private List<Species> caredSpecies = new LinkedList<>();

    public GondoZoo(String lName, String fName, LocalDate bDate, Gender g, List c) {
        lastName = lName;

        firstName = fName;

        birthDate = bDate;

        gender = g;

        caredSpecies = c;
    }

    public void addSpecies(Species spec) {
        caredSpecies.add(spec);
    }
    public void printCaredAnimals(){
        for (Species s: caredSpecies){
            System.out.println(s+" ");
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {

            return true;
        }
        if (!(obj instanceof GondoZoo)) {

            return false;
        }
        GondoZoo g = (GondoZoo) obj;

        return lastName.equals(g.lastName) &&
                firstName.equals(g.firstName) &&
                birthDate.equals(g.birthDate) &&
                gender.toString().equals(g.gender.toString());
    }
}
