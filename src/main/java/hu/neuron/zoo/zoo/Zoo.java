package hu.neuron.zoo.zoo;

import hu.neuron.zoo.animals.Animal;
import hu.neuron.zoo.employee.*;
import hu.neuron.zoo.enumsofzoo.Species;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class Zoo {

    /*
    *Lehetőséget ad az állatkert költözésére egy másik állatkertbe. (Beágyazott osztály segítségével.
    */
    public static class Move {

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

    @Getter
    private static int zooCounter = 0;

    static {
        howManyZoos();
    }

    @Getter
    private Director director;

    private List<GondoZoo> listOfGondozo;

    private List<Animal> listOfAnimal;

    {
        listOfGondozo = new LinkedList<GondoZoo>();

        listOfAnimal = new LinkedList<Animal>();

        zooCounter++;

        foundedZoo();

        emptyZoo();
    }

    public Zoo() {
    }

    public static void howManyZoos() {

        System.out.println("Az országnak " + zooCounter + " állatkertje van jelenleg!");
    }

    private void foundedZoo() {

        System.out.println("Az állatkert megalapulása: " + LocalDate.now() + ".");
    }

    private void emptyZoo() {

        System.out.println("Az állatkert sajnos még üres!");
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

    //befejezetlen
    //először megnézi, hogy van e olyan állat, amit a Gondozó gondozott, ha van akkor megnézi van e olyan gondozó aki el
    // tudja látni, ha nincs akkor kiírja hogy szüksége van ilyen állatfajtagondozóra
    public void fire(GondoZoo g) {

        if (listOfGondozo == null) {
            System.out.print("Nincs dolgozó!");
        }

        for (GondoZoo key : listOfGondozo) {

            if (key.equals(g)) {

                List<Species> s = key.getCaredSpecies();

                listOfGondozo.remove(key);

                for (Species spec : s) {

                }

                return;
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
                System.out.format("\tGondozó: %s, és a gondozott állat: %s%n", g.getName(), g.getCaredSpecies());
            }
        }
    }

    public void addAnimal(Animal a) {
        //hibás nemírja ki létrehozáskor hogy szüksége van ilyen meg olyan dolgozóra
        for (GondoZoo g : listOfGondozo) {

            List<Species> caredSpecies = g.getCaredSpecies();

            if (caredSpecies.contains(a.getSpecies())) {
                listOfAnimal.add(a);
            } else {
                System.out.printf("Az állatkertnek szüksége van %s gondozóra!\n", a.getSpecies());
            }
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

    /*
     * Sorts the animal by species within that by nickname.
    */
    public void printSortedAnimalByNickname() {
        //anonim
        listOfAnimal.stream()
                .sorted(Comparator.comparing(Animal::getSpecies).thenComparing(Animal::getNickName))
                .map(s -> s.getNickName())
                .forEach(System.out::println);
    }
}
