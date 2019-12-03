package hu.neuron.zoo.app;

import hu.neuron.zoo.model.animals.Animal;
import hu.neuron.zoo.model.employees.*;
import hu.neuron.zoo.model.enums.*;
import hu.neuron.zoo.model.exceptions.GondozooNotAvailableException;
import hu.neuron.zoo.model.exceptions.ZooEmployeeException;
import hu.neuron.zoo.model.properties.LanguageProperties;
import hu.neuron.zoo.model.serializer.ZooSerializer;
import hu.neuron.zoo.model.zoo.Zoo;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws ZooEmployeeException, GondozooNotAvailableException, IOException {

        LanguageProperties props=new LanguageProperties("exception.properties");

        System.out.print("a ");
        Zoo firstZoo = new Zoo();

        System.out.print("b ");
        Director erik = new Director("Erik", "Hidi", LocalDate.of(2000, 7, 25), Gender.MALE);
        firstZoo.employ(erik, LocalDate.now());

        System.out.print("c ");
        Animal sanyi = new Animal(Species.GIRAFFE, "Sándor", LocalDate.of(1998, 7, 23), Gender.MALE);
        try {
            firstZoo.add(sanyi);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        System.out.print("d ");
        GondoZoo feco = new GondoZoo("Fecó", "Szabó", LocalDate.of(1997, 12, 4), Gender.MALE);
        feco.add(Species.GIRAFFE);
        firstZoo.employ(feco, LocalDate.of(2010, 1, 1));

        System.out.print("e ");
        try {
            firstZoo.add(sanyi);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        System.out.print("f ");
        Director roland = new Director("Roland", "Kállai", LocalDate.of(1998, 7, 23), Gender.MALE);
        firstZoo.employ(roland, LocalDate.of(2010, 1, 1));

        System.out.println("g ");
        GondoZoo robi = new GondoZoo("Róbert", "Dékány", LocalDate.of(1997, 11, 20), Gender.MALE);
        robi.add(Species.DOLPHIN);
        robi.add(Species.MONKEY);
        robi.doWork(LocalDate.of(2019, 11, 13), Species.MONKEY);
        robi.add(Species.ZEBRA);
        firstZoo.employ(robi, LocalDate.now());

        System.out.print("h ");
        Animal zsuzsi = new Animal(Species.DOLPHIN, "Zsuzsi", LocalDate.of(1998, 7, 23), Gender.FEMALE);
        try {
            firstZoo.add(zsuzsi);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        System.out.println("i\nAz állatkert állatai:");
        firstZoo.printAnimals();

        System.out.print("k ");
        Zoo secondZoo = new Zoo();

        System.out.print("l ");
        Zoo.printHowManyZoos();
        firstZoo.printReward();

        System.out.print("m ");
        Zoo.Move.moved(firstZoo, secondZoo);

        System.out.print("n ");
        firstZoo.printAnimals();

        try {
            firstZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        System.out.print("o ");
        secondZoo.printAnimals();

        try {
            secondZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        System.out.print("p ");
        secondZoo.fire();

        System.out.print("q ");
        try {
            secondZoo.fire(feco);
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        System.out.print("r ");
        secondZoo.remove(sanyi);

        System.out.print("s ");
        secondZoo.remove(zsuzsi);

        System.out.print("t ");
        try {
            secondZoo.fire(robi);
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        System.out.print("u ");
        secondZoo.printAnimals();

        try {
            secondZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        secondZoo.printReward();

        System.out.println("2-es feladatsor:");

        robi.doWork(LocalDate.now(), Species.TIGER);
        robi.doWork(LocalDate.now(), Species.DOLPHIN);
        robi.doWork((LocalDate.now()), Places.POOL);
        robi.doWork(LocalDate.of(2019, 11, 12), Species.MONKEY);
        feco.doWork(LocalDate.of(2019, 1, 1), Species.GIRAFFE);
        Swabber peti = new Swabber("Péter", "Versényi", LocalDate.of(1, 1, 1), Gender.MALE);
        peti.add(Places.TERRARIUM);
        peti.doWork(LocalDate.of(2, 2, 2), Places.CAGE);
        secondZoo.employ(peti, LocalDate.now());
        peti.doWork(LocalDate.now(), Places.TERRARIUM);
        secondZoo.printStoredWorks();

        try {
            secondZoo.add(sanyi);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        try {
            secondZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        secondZoo.printHowManyAnimal();

        System.out.println("firstzoo");

        Animal juli = new Animal(Species.TIGER, "Juli", LocalDate.of(1, 1, 1), Gender.FEMALE);

        try {
            firstZoo.add(juli);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }
        GondoZoo andris = new GondoZoo("andris", "berki", LocalDate.of(2000, 1, 1), Gender.MALE);
        andris.add(Species.TIGER);
        firstZoo.employ(andris, LocalDate.now());

        try {
            firstZoo.add(juli);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        firstZoo.employ(robi, LocalDate.now());
        firstZoo.printAnimals();
        try {
            firstZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        try {
            firstZoo.fire(andris);
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        try {
            firstZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }
        robi.doWork(LocalDate.of(2019, 11, 14), Species.DOLPHIN);

        secondZoo.printStoredWorks();

        System.out.println("MOVE");

        try {
            Zoo.Move.moved(secondZoo, firstZoo);
        } catch (ZooEmployeeException e) {
            System.out.println(e.getMessage());
        }

        try {
            firstZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        firstZoo.employ(feco, LocalDate.now());
        firstZoo.printReward();
        Animal zoli = new Animal(Species.DOLPHIN, "Zoli", LocalDate.of(2010, 1, 1), Gender.MALE);
        Animal bence = new Animal(Species.GIRAFFE, "Bence", LocalDate.of(2000, 1, 1), Gender.MALE);

        try {
            firstZoo.add(zoli);
            firstZoo.add(bence);
        } catch (GondozooNotAvailableException e) {
            System.out.println(props.props.getProperty("MISSING_GONDOZOO"));
        }

        firstZoo.printAnimals();

        firstZoo.printAnimals(Species.GIRAFFE);

        System.out.println("SERIALIZING");
        ZooSerializer zs = new ZooSerializer(firstZoo, "src/main/resources/zoo.txt");

        zs.Serializing();

        try {
            firstZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        Zoo thirdZoo = new Zoo();
        System.out.println("DESERIALIZING");
        thirdZoo = zs.Deserializing();

        try {
            thirdZoo.printEmployees();
        } catch (ZooEmployeeException e) {
            System.out.println(props.props.getProperty("MISSING_DIRECTOR"));
        }

        thirdZoo.printAnimals();
    }


}
