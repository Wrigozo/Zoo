package hu.neuron.zoo;

import hu.neuron.zoo.animals.Animal;
import hu.neuron.zoo.employee.Director;
import hu.neuron.zoo.employee.GondoZoo;
import hu.neuron.zoo.enumsofzoo.Gender;
import hu.neuron.zoo.enumsofzoo.Species;
import hu.neuron.zoo.zoo.Zoo;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws Exception {
        //a
        System.out.print("a ");
        Zoo firstZoo=new Zoo();
        //b
        System.out.print("b ");
        Director erik=new Director("Erik", "Hidi", LocalDate.of(2000,7,25), Gender.MAlE);
        firstZoo.employ(erik);

        //c
        System.out.print("c ");
        Animal sanyi=new Animal(Species.GIRAFFE, "Sándor",LocalDate.of(1998,7,23),Gender.MAlE);
        firstZoo.addAnimal(sanyi);
        //d
        System.out.print("d ");
        GondoZoo feco=new GondoZoo("Fecó", "Szabó", LocalDate.of(1997, 12,4), Gender.MAlE, Arrays.asList(Species.GIRAFFE));
        firstZoo.employ(feco);
        //e
        System.out.print("e ");
        firstZoo.addAnimal(sanyi);
        //f
        System.out.print("f ");
        Director roland=new Director("Roland", "Kállai", LocalDate.of(1998,7,23), Gender.MAlE);
        firstZoo.employ(roland);
        //g
        System.out.println("g");
        GondoZoo robi=new GondoZoo("Róbert", "Dékány", LocalDate.of(1997,11,20), Gender.MAlE, Arrays.asList(Species.DOLPHIN));
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
        Zoo.howManyZoos();
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
        secondZoo.fire(new GondoZoo("Fecó", "Szabó", LocalDate.of(1997, 12,4), Gender.MAlE, new LinkedList()));
        //r
        System.out.print("r ");
        secondZoo.removeAnimal(new Animal(Species.GIRAFFE, "Róland",LocalDate.of(1998,7,23),Gender.MAlE));
        //s
        System.out.print("s ");
        secondZoo.removeAnimal(new Animal(Species.DOLPHIN, "Zsuzsi",LocalDate.of(1998,7,23),Gender.FEMALE));
        //t
        System.out.print("t ");
        secondZoo.fire(new GondoZoo("Róbert", "Dékány", LocalDate.of(1997,11,20), Gender.MAlE, new LinkedList()));
        //u
        System.out.print("u ");
        secondZoo.printAnimals();
        secondZoo.printEmployees();
        firstZoo.printGondoZoos();

        System.out.println("HIBA");
        secondZoo.employ(new GondoZoo("zslt", "bhe", LocalDate.of(2000,1,1), Gender.MAlE, new LinkedList()));
        secondZoo.fire(new GondoZoo("zslt", "bhe", LocalDate.of(2000,1,1), Gender.MAlE, new LinkedList()));

    }

}
