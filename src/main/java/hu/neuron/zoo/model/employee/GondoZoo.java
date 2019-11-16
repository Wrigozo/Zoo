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
     * A {@code List}&lt;{@code Species}&gt; type object, reprezents the list of the {@code Species} that cared by the {@code GondoZoo}.
     */
    private List<Species> listOfCaredSpecies = new LinkedList<>();

    /**
     * A {@code Map}&lt;{@code LocalDate}, {@code Species}&gt; type object, that stores the work done.
     */
    private Map<LocalDate, Species> doneWork = new HashMap<LocalDate, Species>();

    private boolean isHaveJob;

    private GondoZoo() {
    }

    public GondoZoo(String lName, String fName, LocalDate bDate, Gender g) {

        super(lName, fName, bDate, g);
    }

    /**
     * Print the reward to those who have been working for more than 5 years.
     */
    @Override
    public void printGivesReward() {
        System.out.println(getName() + " gondozó rendkívül jól dolgozott az elmúlt 5 év folyamán, így jutalomban részesül! Gratulálunk!");
    }

    /**
     * Adds the given object to the {@code #caredSpecies}.
     *
     * @param spec a {@code Species} type of object, that will be added to {@code List}&lt;{@code Species}&gt; {@code #caredSpecies}.
     */
    public boolean addSpecies(Species spec) {
        return listOfCaredSpecies.add(spec);
    }

    public void printCaredAnimals() {

        for (Species s : listOfCaredSpecies) {
            System.out.println(s + " ");
        }
    }

    /**
     * Stores the work done.
     * First examines that the {@code GondoZoo} has job, then puts the params to the {@code doneWork}. After all puts the
     * {@code GondoZoo} and the {@code #doneWork} to {@code Employee#storedWorks}. It also handles some error cases.
     *
     * @param endTimeOfTask {@code LocalDate} object, that will be put to the {@code #doneWork}
     * @param e             {@code Enum} object, that will be put to the {@code #doneWork}, if {@code #listOfCaredSpecies} contains it
     */
    @Override
    public void doWork(LocalDate endTimeOfTask, Enum e) {
        try {
            if (isHaveJob == true) {
                if (listOfCaredSpecies.contains(e)) {
                    doneWork.put(endTimeOfTask, (Species) e);
                    storedWorks.put(this, doneWork);
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
