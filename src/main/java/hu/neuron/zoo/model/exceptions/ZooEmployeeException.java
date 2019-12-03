package hu.neuron.zoo.model.exceptions;

import hu.neuron.zoo.model.employees.Director;
import hu.neuron.zoo.model.enums.Species;

import java.io.IOException;

/**
 * An exception class for employee not available.
 */
public class ZooEmployeeException extends ZooException {

    String message=props.getProperty("MISSING_GONDOZOO");

    public ZooEmployeeException(String msg) {
        super(msg);
    }

    public ZooEmployeeException(Species s) {
        super(s.toString()+" "+props.getProperty("MISSING_GONDOZOO"));
    }

    public ZooEmployeeException(Director d) throws IOException {
        super(props.getProperty("MISSING_DIRECTOR"));
    }

}
