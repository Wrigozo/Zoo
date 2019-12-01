package hu.neuron.zoo.model.exceptions;

import hu.neuron.zoo.model.employees.Director;
import hu.neuron.zoo.model.enums.Species;

import java.util.Collection;

public class ZooEmployeeException extends ZooException {

    public ZooEmployeeException(String msg) {
        super(msg);
    }

    public ZooEmployeeException(Species s) {
        super("Az állatkertnek szüksége van " + s + " gondozóra!\n");
        }

    public ZooEmployeeException(Director d) {
        super("Az állatkertnek nincs jelenleg igazgatója!");
       }

}
