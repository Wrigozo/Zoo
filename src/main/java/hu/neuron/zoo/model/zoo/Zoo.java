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
         *
         * @param from {@code Zoo} object, that will be moved
         * @param to   {@code Zoo} object, where {@code from} will be moved
         */
        public static void moved(Zoo from, Zoo to) {

            for (GondoZoo key : from.listOfGondoZoos) {
                key.setHaveJob(false);
                to.employ(key);
            }

            from.listOfGondoZoos.clear();

            if (to.director == null) {
                to.employ(from.director);
            }

            from.fire();

            for (Animal a : from.listOfAnimals) {
                to.addAnimal(a);
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

    public void employ(Director d) {

        if (director == null) {

            director = d;

            System.out.println("Az állatkert igazgatója " + director.getName() + " lett!");

        } else
            System.out.println("Csak egy igazgató lehet!");
    }

    public void employ(GondoZoo g) {
        if (!g.isHaveJob()) {
            if (!listOfGondoZoos.contains(g)) {

                listOfGondoZoos.add(g);
                g.setHaveJob(true);

            } else {

                System.out.println("Ez a gondozó már itt dolgozik!");
            }
        } else {
            System.out.println(g.getName() + " már munkavállaló máshol, sajnos nem alkalmazhatjuk. :(");
        }
    }

    public void employ(Swabber s) {
        if (s.isHaveJob() == false) {
            if (!listOfSwabbers.contains(s)) {

                listOfSwabbers.add(s);
                s.setHaveJob(true);

            } else {

                System.out.println("Ez a takarító már itt dolgozik!");
            }
        } else {
            System.out.println(s.getName() + " már munkavállaló máshol, sajnos nem alkalmazhatjuk. :(");
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

    public void fire(GondoZoo g) {

        if (listOfGondoZoos == null) {
            System.out.print("Nincs dolgozó!");
        } else {

            listOfGondoZoos.remove(g);

            if (listOfGondoZoos.contains(g)) {
                System.out.println("Nem sikerült a gondozót kirúgni!");

            } else {
                g.setHaveJob(false);

                if (isSpeciesCaredBy(g)) {

                    for (GondoZoo go : listOfGondoZoos) {
                        for (Species sg : g.getListOfCaredSpecies()) {

                            if (!go.getListOfCaredSpecies().contains(sg)) {
                                System.out.printf("Az állatkertnek szüksége van %s gondozóra!\n", sg);
                            }
                        }
                    }
                }
            }
        }
    }

    public void fire(Swabber s) {

        if (listOfSwabbers == null) {
            System.out.print("Nincs dolgozó!");
        } else {

            listOfSwabbers.remove(s);

            if (listOfSwabbers.contains(s)) {
                System.out.println("Nem sikerült a takarítót kirúgni!");

            } else {
                s.setHaveJob(false);
            }
        }
    }

    public void addAnimal(Animal a) {

        if (isContainsSpecies(a)) {
            listOfAnimals.add(a);
        } else {
            System.out.printf("Az állatkertnek szüksége van %s gondozóra!\n", a.getSpecies());
        }
    }

    public void removeAnimal(Animal a) {

        for (Animal an : listOfAnimals) {

            if (an.equals(a)) {
                listOfAnimals.remove(an);
            }
        }
    }

    public void printHowManyAnimal() {

        System.out.println("Az állatkertnek " + listOfAnimals.size() + " lakója van jelenleg!");
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
                System.out.format("\tGondozó: %s, és a gondozott állat: %s%n", g.getName(), g.getListOfCaredSpecies());
            }
        }
    }

    public void printSwabbers() {

        if (listOfSwabbers.isEmpty()) {
            System.out.println("Nincs takarító!");
        } else {
            for (Swabber s: listOfSwabbers) {
                System.out.format("\tTakarító: %s, és a takarított hely: %s%n", s.getName(), s.getListOfCleanedPlaces());
            }
        }
    }

    public void printAnimals() {

        if (listOfAnimals != null) {

            System.out.print("Az állatok: ");

            for (Animal a : listOfAnimals) {
                System.out.print(a.getNickName() + " ");
            }

            System.out.println();
        }
    }

    /**
     * Sorts the animal by species within that by nickname.
     */
    public void printSortedAnimalByNickname() {
        listOfAnimals.stream()
                .sorted(Comparator.comparing(Animal::getSpecies).thenComparing(Animal::getNickName))
                .map(s -> s.getNickName())
                .forEach(System.out::println);
    }


    /**
     * isSpeciesCaredBy
     *
     * @param g
     * @return
     */
    private boolean isSpeciesCaredBy(GondoZoo g) {

        for (Animal a : listOfAnimals) {

            if (g.getListOfCaredSpecies().contains(a.getSpecies())) {
                return true;
            }
        }
        return false;
    }


    private boolean isContainsSpecies(Animal a) {

        for (GondoZoo g : listOfGondoZoos) {

            if (g.getListOfCaredSpecies().contains(a.getSpecies())) {
                return true;
            }
        }
        return false;
    }
}
