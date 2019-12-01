package hu.neuron.zoo.model.employees;

import hu.neuron.zoo.model.enums.Gender;
import hu.neuron.zoo.model.enums.Species;

import java.time.LocalDate;

public class GondoZoo extends GenericEmployee<Species> implements Work {

    private GondoZoo() {
    }

    public GondoZoo(String lName, String fName, LocalDate bDate, Gender g) {

        super(lName, fName, bDate, g);
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
                if (providingList.contains(e)) {
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
                providingList.equals(g.providingList);
    }
}
