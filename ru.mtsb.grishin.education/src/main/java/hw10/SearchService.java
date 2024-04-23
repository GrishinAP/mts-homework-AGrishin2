package hw10;

import hw6.Animal;
import hw6.invalidAnimalBirthDateException;

public interface SearchService {
    Boolean searchService(Animal animal)  throws invalidAnimalBirthDateException;
}
