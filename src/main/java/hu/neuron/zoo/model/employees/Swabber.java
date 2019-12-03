package hu.neuron.zoo.model.employees;

import hu.neuron.zoo.model.enums.Gender;
import hu.neuron.zoo.model.enums.Places;

import java.time.LocalDate;

public class Swabber extends GenericEmployee<Places> implements Work {

    private Swabber() {
    }

    public Swabber(String lName, String fName, LocalDate bDate, Gender g) {

        super(lName, fName, bDate, g);
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
                if (providingList.contains(e)) {

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
                providingList.equals(s.providingList);
    }
}
