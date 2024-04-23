package hw10;

import hw6.Animal;
import hw6.invalidAnimalBirthDateException;

import java.time.LocalDate;


public class SearchServiceImpl implements SearchService {
    public Boolean searchService(Animal animal) throws invalidAnimalBirthDateException {
            Boolean returnValue =Boolean.FALSE;
            if (animal == null)
                //throw    new InvalidAnimalException("на вход пришло некорректный объект животного. " + LocalDate.now());
                throw    new RuntimeException("на вход пришло некорректный объект животного. " + LocalDate.now());
            if (animal.getBirthday() == null)
                throw new invalidAnimalBirthDateException("у животного" + animal.getBreed() + " не указана дата его рождения" + LocalDate.now());
            StringBuilder str = new StringBuilder(animal.getName());
            if (animal.getBirthday().isLeapYear()) {
                str.append(" был рожден в високосный год");
                returnValue = Boolean.TRUE;
            }else str.append(" не был рожден в високосный год");
            System.out.println(str);
            return returnValue;
    }
}
