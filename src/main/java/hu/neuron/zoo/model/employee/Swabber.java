package hu.neuron.zoo.model.employee;

import hu.neuron.zoo.model.enumsofzoo.Places;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Swabber extends Employee{

    private List<Places> cleanedPlaces=new LinkedList<Places>();

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {

            return true;
        }
        if (!(obj instanceof Swabber)) {

            return false;
        }
        Swabber s = (Swabber) obj;

        return  lastName.equals(s.lastName) &&
                firstName.equals(s.firstName) &&
                birthDate.equals(s.birthDate) &&
                gender.toString().equals(s.gender.toString()) &&
                cleanedPlaces.equals(s.cleanedPlaces);
    }


}
