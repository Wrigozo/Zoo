package hu.neuron.zoo.model.employee;

import java.time.LocalDate;
import java.util.List;

public interface Work {

    void doWork(LocalDate endTimeOfTask, Enum e);
}
