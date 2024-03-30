package hw6;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public interface AnimalRepository {
    HashMap<String, LocalDate> findLeapYearNames(Animal[] animals) throws invalidAnimalBirthDateException;

    HashMap<Animal, Integer> findOlderAnimal(Animal[] animals, Integer n) throws invalidAnimalBirthDateException;

    HashMap<String, List<Animal>> findDuplicate(Animal[] animals) throws invalidAnimalBirthDateException;
    Double findAverageAge(Animal[] animals);
    List<Animal> findOldAndExpensive(Animal[] animals);
    List<Animal> findMinConstAnimals(Animal[] animals);

}
