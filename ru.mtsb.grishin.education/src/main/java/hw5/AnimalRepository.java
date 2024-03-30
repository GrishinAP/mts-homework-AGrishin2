package hw5;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

public interface AnimalRepository {
    HashMap<String, LocalDate> findLeapYearNames(Animal[] animals)throws invalidAnimalBirthDateException;
    HashMap<Animal, Integer> findOlderAnimal(Animal[] animals, Integer n)throws invalidAnimalBirthDateException;
    HashMap<String, Integer> findDuplicate(Animal[] animals)throws invalidAnimalBirthDateException;
}
