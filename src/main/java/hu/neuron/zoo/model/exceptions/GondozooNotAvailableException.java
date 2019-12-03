package hu.neuron.zoo.model.exceptions;

import hu.neuron.zoo.model.enums.Species;

/**
 * An exception class for gondozoo not available.
 */
public class GondozooNotAvailableException extends ZooException {

    String message=props.getProperty("MISSING_GONDOZOO");

    public GondozooNotAvailableException(String msg) {
        super(msg);
    }

    public GondozooNotAvailableException(Species s) {
        System.out.println(s+" "+props.getProperty("MISSING_GONDOZOO"));

    }
}
