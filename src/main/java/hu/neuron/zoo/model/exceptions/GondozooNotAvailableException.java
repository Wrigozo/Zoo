package hu.neuron.zoo.model.exceptions;

import hu.neuron.zoo.model.enums.Species;

/**
 * An exception class for gondozoo not available.
 */
public class GondozooNotAvailableException extends ZooException {

    public GondozooNotAvailableException(String msg) {
        super(msg);
    }

    public GondozooNotAvailableException(Species s) {
    }
}
