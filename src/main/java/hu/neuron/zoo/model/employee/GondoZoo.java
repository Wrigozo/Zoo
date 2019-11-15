package hu.neuron.zoo.model.employee;

import hu.neuron.zoo.model.enumsofzoo.Gender;
import hu.neuron.zoo.model.enumsofzoo.Species;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GondoZoo extends Employee implements Work {

    /**
     * A {@code List}&lt;{@code Species}&gt; type object, reprezents the list of the species that cared by the {@link GondoZoo}.
     */
    private List<Species> listOfCaredSpecies = new LinkedList<>();

    private Map<LocalDate, Species> done = new HashMap<LocalDate, Species>();

    private boolean isHaveJob;

    private GondoZoo() {
    }

    public GondoZoo(String lName, String fName, LocalDate bDate, Gender g) {

        super(lName,fName,bDate,g);
    }

    /**
     * Adds the given object to the {@code List}&lt;{@link Species}&gt; caredSpecies.
     *
     * @param spec a {@code Species} type of object, that will be added to {@code List}&lt;{@link Species}&gt; caredSpecies.
     */
    public boolean addSpecies(Species spec) {
        return listOfCaredSpecies.add(spec);
    }

    public void printCaredAnimals() {

        for (Species s : listOfCaredSpecies) {
            System.out.println(s + " ");
        }
    }

    @Override
    public void doWork(LocalDate endTimeOfTask, Enum e) {
        try {
            if (isHaveJob == true) {
                if (listOfCaredSpecies.contains(e)) {
                    done.put(endTimeOfTask, (Species) e);
                    storedWorks.put(this, done);
                } else {
                    System.out.println("Ezt a fajtát nem gondozza " + getName());
                }
            } else {
                System.out.println(getName() + " nem tud munkát végezni, ha nincs állása.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Ez nem egy állat!");
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
                gender.toString().equals(g.gender.toString()) &&
                listOfCaredSpecies.equals(g.listOfCaredSpecies);
    }
}
