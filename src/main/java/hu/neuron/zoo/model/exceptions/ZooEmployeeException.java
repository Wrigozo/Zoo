package hu.neuron.zoo.model.exceptions;

import hu.neuron.zoo.model.employees.Director;
import hu.neuron.zoo.model.enums.Species;

/**
 * An exception class for employee not available.
 */
public class ZooEmployeeException extends ZooException {

    public ZooEmployeeException(String msg) {
        super(msg);
    }

    public ZooEmployeeException(Species s) {
    }

    public ZooEmployeeException(Director d) {
    }

}
