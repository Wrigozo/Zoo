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
     * A {@code List}&lt;{@code Places}&gt; type object, reprezents the list of the places that cleaned by the {@link Swabber}.
     */
    private List<Places> listOfCleanedPlaces = new LinkedList<Places>();

    private Map<LocalDate, Places> done = new HashMap<LocalDate, Places>();

    private boolean isHaveJob;

    private Swabber() {
    }

    public Swabber(String lName, String fName, LocalDate bDate, Gender g) {

       super(lName,fName,bDate,g);
    }

    public void addPlaces(Places p) {
        listOfCleanedPlaces.add(p);
    }

    @Override
    public void doWork(LocalDate endTimeOfTask, Enum e) {
        try {
            if (isHaveJob == true) {
                if (listOfCleanedPlaces.contains(e)) {
                    done.put(endTimeOfTask, (Places) e);
                    storedWorks.put(this, done);
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
