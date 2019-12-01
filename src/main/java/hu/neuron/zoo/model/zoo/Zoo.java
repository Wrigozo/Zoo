package hu.neuron.zoo.model.zoo;

import hu.neuron.zoo.model.animals.Animal;
import hu.neuron.zoo.model.employees.*;
import hu.neuron.zoo.model.enums.Species;
import hu.neuron.zoo.model.exceptions.GondozooNotAvailableException;
import hu.neuron.zoo.model.exceptions.ZooEmployeeException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * Represents a zoo.
 */
@Getter
@Setter
public class Zoo implements Serializable {

    /**
     * Gives a the possibility to move the {@code Zoo} to another {@code Zoo}.
     */
    public static class Move {

        /**
         * Moves the first {@code Zoo} to the second {@code Zoo}.
         *
         * @param from {@code Zoo} object, that will be moved
         * @param to   {@code Zoo} object, where {@code #from} will be moved
         */
        public static void moved(Zoo from, Zoo to) throws ZooEmployeeException, GondozooNotAvailableException {

            for (GondoZoo g : from.listOfGondoZoos) {
                g.setHaveJob(false);
                to.employ(g, g.getStartWorkingDate());
            }

            from.listOfGondoZoos.clear();
            if (to.director == null) {
                if (from.director != null) {
                    to.employ(from.director, LocalDate.now());
                } else {
                    to.director = null;
                }
            } else {
                System.out.println(to.director.getName());
                from.director = null;
            }

            from.fire();

            for (Swabber s : from.listOfSwabbers) {
                s.setHaveJob(false);
                to.employ(s, s.getStartWorkingDate());
            }

            from.listOfGondoZoos.clear();

            for (Animal a : from.listOfAnimals) {
                to.add(a);
            }

            from.listOfAnimals.clear();
        }
    }

    private static int zooCounter = 0;

    static {
        printHowManyZoos();
    }

    private Director director;

    private List<GondoZoo> listOfGondoZoos;

    private List<Swabber> listOfSwabbers;

    private List<Animal> listOfAnimals;

    {
        listOfGondoZoos = new LinkedList<GondoZoo>();

        listOfSwabbers = new LinkedList<Swabber>();

        listOfAnimals = new LinkedList<Animal>();

        zooCounter++;

        printFoundedZoo();

        printEmptyZoo();
    }

    public Zoo() {
    }

    /**
     * Employs a director.
     *
     * @param d  a {@code Direktor} object, who will be employed
     * @param da a {@code LocalDate} object, when the {@code Direktor} starts working
     */
    public void employ(Director d, LocalDate da) {

        if (director == null) {

            director = d;

            director.setStartWorkingDate(da);

            System.out.println("Az állatkert igazgatója " + director.getName() + " lett!");

        } else
            System.out.println("Csak egy igazgató lehet!");
    }

    /**
     * Employs a gondozoo.
     * First, examines that the {@code GondoZoo} has a job or no. If he doesn't have it, then checks: is the {@code #listOfGondoZoos}
     * contains {@code GondoZoo} or no. If he doesn't member of it, he haven't been employed yet, so could employ him.
     * After all, sets the first working date of the {@code GondoZoo}, and also he has a job now. Finally, this also handles some error cases.
     *
     * @param g  a {@code GondoZoo} object, who will be employed
     * @param da a {@code LocalDate} object, when the {@code GondoZoo} starts working
     */
    public void employ(GondoZoo g, LocalDate da) {

        if (!g.isHaveJob()) {
            if (!listOfGondoZoos.contains(g)) {

                listOfGondoZoos.add(g);
                g.setStartWorkingDate(da);
                g.setHaveJob(true);

            } else {

                System.out.println("Ez a gondozó már itt dolgozik!");
            }
        } else {
            System.out.println(g.getName() + " már munkavállaló máshol, sajnos nem alkalmazhatjuk. :(");
        }
    }

    /**
     * Employs a Swabber.
     * First, examines that the {@code Swabber} has a job or no. If he doesn't have it, then checks: is the {@code #listOfSwabbers}
     * contains {@code Swabber} or no. If he doesn't member of it, he haven't been employed yet, so could employ him.
     * After all, sets the first working date of the {@code Swabber}, and also he has a job now. Finally, also handles some error cases.
     *
     * @param s  a {@code Swabber} object, who will be employed
     * @param da a {@code LocalDate} object, when the {@code Swabber} starts working
     */
    public void employ(Swabber s, LocalDate da) {
        if (s.isHaveJob() == false) {
            if (!listOfSwabbers.contains(s)) {

                listOfSwabbers.add(s);
                s.setStartWorkingDate(da);
                s.setHaveJob(true);

            } else {

                System.out.println("Ez a takarító már itt dolgozik!");
            }
        } else {
            System.out.println(s.getName() + " már munkavállaló máshol, sajnos nem alkalmazhatjuk. :(");
        }

    }

    /**
     * Fires the director.
     */
    public void fire() throws ZooEmployeeException {

        if (director == null) {
            throw new ZooEmployeeException(director);
        } else {

            System.out.println("Az állatkert " + director.getName() + " igazgatója eltávozott!");
            director = null;
        }
    }

    /**
     * Fires the gondozo.
     * First, examines that the list of the {@code GondoZoo} is null or no. If it is not null, tries to remove the {@code GondoZoo}.
     * If it isn't possible, handles it. Else sets the {@code GondoZoo} doesn't have job, his first working date will be null.
     * After all, examines that : are there any species of animal cared by the {@code GondoZoo} in the zoo. Finally, if it is true,
     * the next question is: are there any {@code GondoZoo} who will care these animals, if there are no-one, these animals need cares.
     *
     * @param g a {@code GondoZoo} object, who will be fired
     */
    public void fire(GondoZoo g) throws ZooEmployeeException {

        if (listOfGondoZoos == null) {
            System.out.print("Nincs dolgozó!");
        } else {

            listOfGondoZoos.remove(g);

            if (listOfGondoZoos.contains(g)) {
                System.out.println("Nem sikerült a gondozót kirúgni!");

            } else {
                g.setHaveJob(false);
                g.setStartWorkingDate(null);

                if (isSpeciesCaredBy(g)) {

                    for (GondoZoo go : listOfGondoZoos) {
                        for (Species sg : g.getProvidingList()) {

                            if (!go.getProvidingList().contains(sg)) {
                                throw new ZooEmployeeException(sg);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Fires the swabber.
     * First, examines that the list of the {@code Swabber} is null or no. If not null, tries to remove this {@code Swabber}.
     * After all, sets the {@code Swabber} doesn't have job, his first working date will be null. If the firing not successfull,
     * handles it.
     *
     * @param s a {@code Swabber} object, who will be fired
     */
    public void fire(Swabber s) {

        if (listOfSwabbers == null) {
            System.out.print("Nincs dolgozó!");
        } else {

            listOfSwabbers.remove(s);
            s.setStartWorkingDate(null);

            if (listOfSwabbers.contains(s)) {
                System.out.println("Nem sikerült a takarítót kirúgni!");

            } else {
                s.setHaveJob(false);
            }
        }
    }

    /**
     * Examines {@link #isCaredSpecies}, if it is true, then adds the animal to the {@code #listOfAnimals} of zoo. Else,
     * the zoo need the species of the animal caring.
     *
     * @param a the {@code Animal}, whose species are examined
     */
    public void add(Animal a) throws GondozooNotAvailableException {

        if (isCaredSpecies(a)) {
            listOfAnimals.add(a);
        } else {
            throw new GondozooNotAvailableException(a);
        }
    }

    public void remove(Animal a) {

        for (Animal an : listOfAnimals) {

            if (an.equals(a)) {
                listOfAnimals.remove(an);
            }
        }
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

    public void printEmployees() {

        printDirector();

        System.out.println("és a dolgozók: ");

        if (listOfGondoZoos == null) {
            System.out.print("Nincs dolgozó!");
        } else
            printGondoZoos();
        System.out.println("és a takarítók: ");

        if (listOfSwabbers == null) {
            System.out.print("Nincs takarító!");
        } else
            printSwabbers();
    }

    public void printDirector() {

        if (director == null) {

            System.out.println("Az állatkertnek nincs jelenleg igazgatója!");
        } else

            System.out.println("Az igazgató: " + director.getName());
    }

    public void printGondoZoos() {

        if (listOfGondoZoos.isEmpty()) {
            System.out.println("Nincs gondozó!");
        } else {
            for (GondoZoo g : listOfGondoZoos) {
                System.out.format("\tGondozó: %s, és a gondozott állat: %s%n", g.getName(), g.getProvidingList());
            }
        }
    }

    public void printSwabbers() {

        if (listOfSwabbers.isEmpty()) {
            System.out.println("Nincs takarító!");
        } else {
            for (Swabber s : listOfSwabbers) {
                System.out.format("\tTakarító: %s, és a takarított hely: %s%n", s.getName(), s.getProvidingList());
            }
        }
    }

    /**
     * Prints the stored works of the zoo.
     * First iterates among the storeWorks keys, and write to the console: the {@code Employee}, and his
     * works done. After that, the storeWorks values are truly maps. So iterates the values(maps), that stores
     * the date of th work, and the tasks done. Writes it to the console. Finally, examines that: is the {@code Employee} {@code GondoZoo} or {@code Swabber} type,
     * and prints the appropriate work of {@code Employee}.
     */
    public void printStoredWorks() {
        for (Employee k : Employee.storedWorks.keySet()) {
            System.out.println("A dolgozó: " + k.getName() + " és az elvégzett munka: ");
            for (Object d : Employee.storedWorks.get(k).keySet()) {
                System.out.println("\t\t idő:" + d + " feladat: " + Employee.storedWorks.get(k).get(d));
            }
            if (k.getClass().getSimpleName().toString().equals("GondoZoo")) {
                System.out.println(" gondozása.");
            } else {
                System.out.println("takarítása.");
            }
        }
    }

    /**
     * Prints the rewards of the gondoZoos and the swabbers.
     */
    public void printReward() {

        for (GondoZoo g : listOfGondoZoos) {
            Period a = g.getStartWorkingDate().until(LocalDate.now());
            if (a.getYears() >= 5) {
                g.printGivesReward();
            }
        }
        for (Swabber s : listOfSwabbers) {
            Period a = s.getStartWorkingDate().until(LocalDate.now());
            if (a.getYears() >= 5) {
                s.printGivesReward();
            }
        }
    }

    public void printHowManyAnimal() {

        System.out.println("Az állatkertnek " + listOfAnimals.size() + " lakója van jelenleg!");
    }

    /**
     * Prints the Animal sorted by species in alphabetical order within that by nickname in alphabetical order.
     */
    public void printAnimals() {

        if (listOfAnimals != null) {

            System.out.println("Az állatok: ");

            listOfAnimals.stream()
                    .sorted(Comparator.comparing(Animal::getNickName))
                    .sorted(Comparator.comparing(animal -> animal.getSpecies().toString()))
                    .forEach(s -> System.out.println("\t" + s.getNickName() + " \tfajtája: " + s.getSpecies()));
        }
    }

    /**
     * Print the animal whose species are the given value.
     *
     * @param s is a {@code Species} enum that by sorted the animals
     */
    public void printAnimals(Species s) {
        System.out.println("A " + s.toString() + " fajtájú állatok: ");
        listOfAnimals.stream()
                .filter(animal -> animal.getSpecies() == s)
                .sorted(Comparator.comparing(Animal::getNickName))
                .forEach(a -> System.out.println("\t" + a.getNickName()));
    }

    /**
     * Examines that : are there any species of animal cared by the {@code GondoZoo} in the zoo.
     *
     * @param g a {@code GondoZoo} type object whose cared animal will be examined
     * @return if there are species of the animal of zoo, that also cares the  {@code GondoZoo}, returns true, else false
     */
    private boolean isSpeciesCaredBy(GondoZoo g) {

        for (Animal a : listOfAnimals) {

            if (g.getProvidingList().contains(a.getSpecies())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Examines that: are the species of the actual {@code Animal} cared in the zoo.
     *
     * @param a an {@code Animal} type object that species is examined
     * @return true, if the species of the actual {@code Animal} cared in the Zoo
     */
    private boolean isCaredSpecies(Animal a) {

        for (GondoZoo g : listOfGondoZoos) {

            if (g.getProvidingList().contains(a.getSpecies())) {
                return true;
            }
        }
        return false;
    }
}
