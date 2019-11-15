package hu.neuron.zoo.model.animals;

import hu.neuron.zoo.model.enumsofzoo.Gender;
import hu.neuron.zoo.model.enumsofzoo.Species;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
/**
 * Reprezents an animal.
 */
public class Animal implements Serializable {

    private Species species;

    private String nickName;

    private LocalDate birthDate;

    private Gender gender;

    private Animal() {
    }

    public Animal(Species s, String nName, LocalDate bDate, Gender g) {

        species = s;

        nickName = nName;

        birthDate = bDate;

        gender = g;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {

            return true;
        }
        if (!(obj instanceof Animal)) {

            return false;
        }
        Animal g = (Animal) obj;

        return  species.equals(g.species) &&
                nickName.equals(g.nickName) &&
                birthDate.equals(g.birthDate) &&
                gender.equals(g.gender);
    }
}
