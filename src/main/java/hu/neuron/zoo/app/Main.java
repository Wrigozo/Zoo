package hu.neuron.zoo.app;

import hu.neuron.zoo.model.animals.Animal;
import hu.neuron.zoo.model.employee.Director;
import hu.neuron.zoo.model.employee.GondoZoo;
import hu.neuron.zoo.model.employee.Swabber;
import hu.neuron.zoo.model.enumsofzoo.Gender;
import hu.neuron.zoo.model.enumsofzoo.Places;
import hu.neuron.zoo.model.enumsofzoo.Species;
import hu.neuron.zoo.model.zoo.Zoo;


import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)  {
        //a
        System.out.print("a ");
        Zoo firstZoo=new Zoo();
        //b
        System.out.print("b ");
        Director erik=new Director("Erik", "Hidi", LocalDate.of(2000,7,25), Gender.MALE);
        firstZoo.employ(erik);

        //c
        System.out.print("c ");
        Animal sanyi=new Animal(Species.GIRAFFE, "Sándor",LocalDate.of(1998,7,23),Gender.MALE);
        firstZoo.addAnimal(sanyi);
        //d
        System.out.print("d ");
        GondoZoo feco=new GondoZoo("Fecó", "Szabó", LocalDate.of(1997, 12,4), Gender.MALE, Arrays.asList(Species.GIRAFFE));
        firstZoo.employ(feco);
        //e
        System.out.print("e ");
        firstZoo.addAnimal(sanyi);
        //f
        System.out.print("f ");
        Director roland=new Director("Roland", "Kállai", LocalDate.of(1998,7,23), Gender.MALE);
        firstZoo.employ(roland);
        //g
        System.out.println("g ");
        GondoZoo robi=new GondoZoo("Róbert", "Dékány", LocalDate.of(1997,11,20), Gender.MALE, Arrays.asList(Species.DOLPHIN));
        firstZoo.employ(robi);
        //h
        System.out.print("h ");
        Animal zsuzsi=new Animal(Species.DOLPHIN, "Zsuzsi",LocalDate.of(1998,7,23),Gender.FEMALE);
        firstZoo.addAnimal(zsuzsi);
        //i
        System.out.println("Az állatkert állatai:");
        firstZoo.printAnimals();
        //j
        System.out.println("Az állatkert állatai rendezve:");
        firstZoo.printSortedAnimalByNickname();
        //k
        System.out.print("k ");
        Zoo secondZoo=new Zoo();
        //l
        System.out.print("l ");
        Zoo.printHowManyZoos();
        //m
        System.out.print("m ");
        Zoo.Move.moved(firstZoo,secondZoo);
        //n
        System.out.print("n ");
        firstZoo.printAnimals();
        firstZoo.printEmployees();
        //o
        System.out.print("o ");
        secondZoo.printAnimals();
        secondZoo.printEmployees();
        //p
        System.out.print("p ");
        secondZoo.fire();
        //q
        System.out.print("q ");
        //secondZoo.fire(feco);
        //r
        System.out.print("r ");
        secondZoo.removeAnimal(sanyi);
        //s
        System.out.print("s ");
        secondZoo.removeAnimal(zsuzsi);
        //t
        System.out.print("t ");
        //secondZoo.fire(robi);
        //u
        System.out.print("u ");
        secondZoo.printAnimals();
        secondZoo.printEmployees();

        System.out.println("\n2-es feladatsor:");

        robi.doWork(LocalDate.now(), Species.TIGER);
        robi.doWork(LocalDate.now(),Species.DOLPHIN);
        robi.doWork((LocalDate.now()), Places.POOL);
        feco.doWork(LocalDate.of(2019,1,1), Species.GIRAFFE);
        Swabber peti=new Swabber("Péter", "Versényi",LocalDate.of(1,1,1), Gender.MALE, Arrays.asList(Places.TERRARIUM));
        peti.doWork(LocalDate.of(2,2,2),Places.CAGE);
        peti.doWork(LocalDate.now(),Places.TERRARIUM);
        secondZoo.printStoredWorks();
        System.out.println("firstzoo");
        Animal juli=new Animal(Species.TIGER,"Juli",LocalDate.of(1,1,1),Gender.FEMALE);
        firstZoo.addAnimal(juli);
        GondoZoo andris=new GondoZoo("andris", "berki", LocalDate.of(2000,1,1), Gender.MALE, Arrays.asList(Species.TIGER));
        firstZoo.employ(andris);
        firstZoo.addAnimal(juli);
        firstZoo.employ(robi);
        firstZoo.printAnimals();
        firstZoo.printEmployees();
//        firstZoo.fire(andris);
//        firstZoo.printEmployees();
        robi.doWork(LocalDate.of(2019,11,14),Species.DOLPHIN);
        secondZoo.printStoredWorks();


    }

}
