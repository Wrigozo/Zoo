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
public class GondoZoo extends Employee implements Work{

    /**
     * A {@code List}&lt;{@code Species}&gt; type object, reprezents the list of the species that cared by the {@link GondoZoo}.
     */
    private List<Species> listOfCaredSpecies = new LinkedList<>();

    private  Map<LocalDate, Species> done=new HashMap<LocalDate, Species>();

    private GondoZoo(){

    }

    public GondoZoo(String lName, String fName, LocalDate bDate, Gender g, List c) {
        lastName = lName;

        firstName = fName;

        birthDate = bDate;

        gender = g;

        listOfCaredSpecies = c;
    }

    /**
     * Adds the given object to the {@code List}&lt;{@link Species}&gt; caredSpecies.
     * @param spec a {@code Species} type of object, that will be added to {@code List}&lt;{@link Species}&gt; caredSpecies.
     */
    public void addSpecies(Species spec) {
        listOfCaredSpecies.add(spec);
    }

    public void printCaredAnimals(){

        for (Species s: listOfCaredSpecies){
            System.out.println(s+" ");
        }
    }
    @Override
    public void doWork(LocalDate endTimeOfTask, Enum e) {
        try{
            if(listOfCaredSpecies.contains(e)){
                done.put(endTimeOfTask, (Species) e);
                storedWorks.put(this, done);
            }
            else{
                System.out.println("Ezt a fajtát nem gondozza " + getName());
            }
        }
        catch (IllegalArgumentException ex){
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

        return  lastName.equals(g.lastName) &&
                firstName.equals(g.firstName) &&
                birthDate.equals(g.birthDate) &&
                gender.toString().equals(g.gender.toString()) &&
                listOfCaredSpecies.equals(g.listOfCaredSpecies);
    }
}
