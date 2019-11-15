package hu.neuron.zoo.model.zoo;

import hu.neuron.zoo.model.animals.Animal;
import hu.neuron.zoo.model.employee.*;
import hu.neuron.zoo.model.enumsofzoo.Species;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Zoo implements Serializable {

    /**
     * Gives a the possibility to move the {@code Zoo} to another {@code Zoo}.
     */
    public static class Move {

        /**
         * Moves the first object to the second object.
         * @param from {@code Zoo} object, that will be moved
         * @param to {@code Zoo} object, where {@code from} will be moved
         */
        public static void moved(Zoo from, Zoo to) {

            for (GondoZoo key : from.listOfGondozo) {
                to.employ(key);
            }

            from.listOfGondozo.clear();

            if (to.director == null) {
                to.employ(from.director);
            }

            from.fire();

            for (Animal a : from.listOfAnimal) {
                to.addAnimal(a);
            }

            from.listOfAnimal.clear();
        }
    }

    private static int zooCounter = 0;

    static {
        printHowManyZoos();
    }

    private Director director;

    private List<GondoZoo> listOfGondozo;

    private List<Animal> listOfAnimal;

    {
        listOfGondozo = new LinkedList<GondoZoo>();

        listOfAnimal = new LinkedList<Animal>();

        zooCounter++;

        printFoundedZoo();

        printEmptyZoo();
    }

    public Zoo() {
    }

    public static void printHowManyZoos() {

        System.out.println("Az országnak " + zooCounter + " állatkertje van jelenleg!");
    }

    private void printFoundedZoo() {

        System.out.println("Az állatkert megalapulása: " + LocalDate.now() + ".");
    }

    private void printEmptyZoo() {

        System.out.println("Az állatkert sajnos még üres!");
    }

    public void printStoredWorks(){
        for(Employee k:Employee.storedWorks.keySet()){
            System.out.println("A dolgozó: "+k.getName()+" és az elvégzett munka: ");
            for(Object d:Employee.storedWorks.get(k).keySet()){
                System.out.println("\t\t idő:"+d+" feladat: "+Employee.storedWorks.get(k).get(d));
            }
            if(k.getClass().getSimpleName().toString().equals("GondoZoo")){
                System.out.println(" gondozása.");
            }
            else{
                System.out.println("takarítása.");
            }
        }
    }

    public void employ(Director d) {

        if (director == null) {

            director = d;

            System.out.println("Az állatkert igazgatója " + director.getName() + " lett!");

        } else
            System.out.println("Csak egy igazgató lehet!");
    }

    public void employ(GondoZoo g) {

        if (!listOfGondozo.contains(g)) {

            listOfGondozo.add(g);
        } else {

            System.out.println("Ez a gondozó már létezik!");
        }
    }

    public void fire() {

        if (director == null) {
            System.out.println("Az állatkertnek nincs jelenleg igazgatója!");
        } else {

            System.out.println("Az állatkert " + director.getName() + " igazgatója eltávozott!");
            director = null;
        }
    }

    public void fire(GondoZoo gondoZoo) {

        if (listOfGondozo == null) {
            System.out.print("Nincs dolgozó!");
        } else {
            listOfGondozo.remove(gondoZoo);

            if (listOfGondozo.contains(gondoZoo)) {
                System.out.println("Nem sikerült a gondozót kirúgni!");
            } else {

                if (isSpeciesCaredBy(gondoZoo)) {

                    for (GondoZoo g : listOfGondozo) {
                        for (Species sg : gondoZoo.getListOfCaredSpecies()) {

                            if (!g.getListOfCaredSpecies().contains(sg)) {
                                System.out.printf("Az állatkertnek szüksége van %s gondozóra!\n", sg);
                            }
                        }
                    }
                }
            }
        }
    }

    public void printEmployees() {

        printDirector();

        System.out.println("és a dolgozók: ");

        if (listOfGondozo == null) {
            System.out.print("Nincs dolgozó!");
        } else
            printGondoZoos();
    }

    public void printDirector() {

        if (director == null) {

            System.out.println("Az állatkertnek nincs jelenleg igazgatója!");
        } else

            System.out.println("Az igazgató: " + director.getName());
    }

    public void printGondoZoos() {

        if (listOfGondozo.isEmpty()) {
            System.out.println("Nincs gondozó!");
        } else {
            for (GondoZoo g : listOfGondozo) {
                System.out.format("\tGondozó: %s, és a gondozott állat: %s%n", g.getName(), g.getListOfCaredSpecies());
            }
        }
    }

    public void addAnimal(Animal a) {

        if (isContainsSpecies(a)) {
            listOfAnimal.add(a);
        } else {
            System.out.printf("Az állatkertnek szüksége van %s gondozóra!\n", a.getSpecies());
        }
    }

    public void removeAnimal(Animal a) {

        for (Animal an : listOfAnimal) {

            if (an.equals(a)) {
                listOfAnimal.remove(an);
            }
        }
    }

    public void printAnimals() {

        if (listOfAnimal != null) {

            System.out.print("Az állatok: ");

            for (Animal a : listOfAnimal) {
                System.out.print(a.getNickName() + " ");
            }

            System.out.println();
        }
    }

    public void printHowManyAnimal() {

        System.out.println("Az állatkertnek " + listOfAnimal.size() + " lakója van jelenleg!");
    }

    /**
     * Sorts the animal by species within that by nickname.
     */
    public void printSortedAnimalByNickname() {
        listOfAnimal.stream()
                .sorted(Comparator.comparing(Animal::getSpecies).thenComparing(Animal::getNickName))
                .map(s -> s.getNickName())
                .forEach(System.out::println);
    }

    /**
     *isSpeciesCaredBy
     * @param gondoZoo
     * @return
     */
    private boolean isSpeciesCaredBy(GondoZoo gondoZoo) {

        for (Animal a : listOfAnimal) {

            if (gondoZoo.getListOfCaredSpecies().contains(a.getSpecies())) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainsSpecies(Animal a) {

        for (GondoZoo g : listOfGondozo) {

            if (g.getListOfCaredSpecies().contains(a.getSpecies())) {
                return true;
            }
        }
        return false;
    }
}
