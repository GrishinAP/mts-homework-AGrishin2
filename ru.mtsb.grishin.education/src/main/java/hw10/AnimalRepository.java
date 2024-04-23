package hw10;

import hw6.Animal;
import hw6.invalidAnimalBirthDateException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface AnimalRepository {
    HashMap<String, LocalDate> findLeapYearNames(hw6.Animal[] animals) throws invalidAnimalBirthDateException;

    HashMap<hw6.Animal, Integer> findOlderAnimal(hw6.Animal[] animals, Integer n) throws invalidAnimalBirthDateException;

    HashMap<String, List<hw6.Animal>> findDuplicate(hw6.Animal[] animals) throws invalidAnimalBirthDateException;
    Double findAverageAge(hw6.Animal[] animals);
    List<hw6.Animal> findOldAndExpensive(hw6.Animal[] animals);
    List<hw6.Animal> findMinConstAnimals(Animal[] animals);

}
