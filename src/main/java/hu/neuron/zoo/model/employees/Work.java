package hu.neuron.zoo.model.employees;

import java.time.LocalDate;

public interface Work {

    void doWork(LocalDate endTimeOfTask, Enum e);
}
