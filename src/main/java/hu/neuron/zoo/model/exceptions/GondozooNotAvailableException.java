package hu.neuron.zoo.model.exceptions;

import hu.neuron.zoo.model.animals.Animal;

import java.util.Collection;

public class GondozooNotAvailableException extends ZooException {

    public GondozooNotAvailableException(String message) {
        super(message);
    }

    public GondozooNotAvailableException(Animal a) {
        super("Az állatkertnek szüksége van " + a.getSpecies() + " gondozóra!\n");
    }
}
