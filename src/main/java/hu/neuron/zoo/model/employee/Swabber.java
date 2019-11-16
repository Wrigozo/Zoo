package hu.neuron.zoo.model.employee;

import hu.neuron.zoo.model.enumsofzoo.Gender;
import hu.neuron.zoo.model.enumsofzoo.Places;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Swabber extends Employee implements Work {

    /**
     * A {@code List}&lt;{@code Places}&gt; type object, reprezents the list of the {@code Places} that cleaned by the {@code Swabber}.
     */
    private List<Places> listOfCleanedPlaces = new LinkedList<Places>();

    /**
     * A {@code Map}&lt;{@code LocalDate},{@code Places}&gt; type object, that stores the work done.
     */
    private Map<LocalDate, Places> doneWork = new HashMap<LocalDate, Places>();

    private boolean isHaveJob;

    private Swabber() {
    }

    public Swabber(String lName, String fName, LocalDate bDate, Gender g) {

        super(lName, fName, bDate, g);
    }

    /**
     * Print the reward to those who have been working for more than 5 years.
     */
    @Override
    public void printGivesReward() {
        System.out.println(getName() + " takarító rendkívül jól dolgozott az évek folyamán, így jutalomban részesül! Gratulálunk!");
    }

    /**
     * Possibility to add {@code Places} to be cleaned to the {@code Swabber}.
     *
     * @param p {@code Places} object, that will be add
     */
    public void addPlaces(Places p) {
        listOfCleanedPlaces.add(p);
    }

    /**
     * Stores the work done.
     * First examines that the {@code Swabber} has job, then puts the params to the {@code doneWork}. After all puts the
     * {@code Swabber} and the {@code #doneWork} to {@code Employee#storedWorks}.
     * It also handles some error cases.
     *
     * @param endTimeOfTask {@code LocalDate} object, that will be put to the {@code #doneWork}
     * @param e             {@code Enum} object, that will be put to the {@code #doneWork}, if {@code #listOfCleanedPlaces} contains it
     */
    @Override
    public void doWork(LocalDate endTimeOfTask, Enum e) {
        try {

            if (isHaveJob == true) {
                if (listOfCleanedPlaces.contains(e)) {

                    doneWork.put(endTimeOfTask, (Places) e);
                    storedWorks.put(this, doneWork);
                } else {

                    System.out.println("Ezt a helyet nem takarítja " + getName());
                }
            } else {
                System.out.println(getName() + " nem tud munkát végezni, ha nincs állása.");
            }

        } catch (IllegalArgumentException ex) {
            System.out.println("Ez nem egy takarítható hely!");
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {

            return true;
        }
        if (!(obj instanceof Swabber)) {

            return false;
        }
        Swabber s = (Swabber) obj;

        return lastName.equals(s.lastName) &&
                firstName.equals(s.firstName) &&
                birthDate.equals(s.birthDate) &&
                gender.toString().equals(s.gender.toString()) &&
                listOfCleanedPlaces.equals(s.listOfCleanedPlaces);
    }
}
