package hu.neuron.zoo.model.employees;

import hu.neuron.zoo.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
abstract class GenericEmployee < E extends Enum>  extends Employee{

    protected GenericEmployee(){}

    protected GenericEmployee(String lName, String fName, LocalDate bDate, Gender g) {
        super(lName, fName, bDate, g);
    }

    /**
     * A {@code Map}&lt;{@code LocalDate}, {@code E}&gt; type object, that stores the work done.
     */
    protected Map<LocalDate, E> doneWork = new HashMap<LocalDate, E>();

    /**
     * A {@code List}&lt;{@code E}&gt; type object, represents the list of the {@code E} that cared by the {@code Employee}.
     */
    protected List<E> providingList = new LinkedList<E>();

    protected boolean isHaveJob;

    /**
     * Adds the given object to the {@code #maintenance}.
     *
     * @param e a {@code E} type of object, that will be added to {@code List}&lt;{@code E}&gt; {@code #maintenance}.
     */
    public boolean add(E e){
         return providingList.add(e);
    }

    /**
     * Print the reward to those who have been working for more than 5 years.
     */
    public void printGivesReward(){
        System.out.println(getName() +" rendkívül jól dolgozott az elmúlt 5 év folyamán, így jutalomban részesül! Gratulálunk!");
    }

    public void printProvidingList() {

        for (E s : providingList) {
            System.out.println(s + " ");
        }
    }


}
