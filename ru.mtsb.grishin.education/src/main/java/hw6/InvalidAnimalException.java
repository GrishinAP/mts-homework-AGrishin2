package hw6;


import hw5.AbstractAnimal;
import hw5.Animal;
import hw5.AnimalRepository;
import hw5.SearchService;
import hw5.SearchServiceImpl;
import hw5.invalidAnimalBirthDateException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InvalidAnimalException extends RuntimeException{
    public InvalidAnimalException(String message){
        super(message);
    }

    public static class AnimalsRepositoryImpl implements AnimalRepository {
        public static final List<String> patAnimals = new ArrayList<String>(Arrays.asList( new String[] {"Dog", "Cat"}));
        public static final List<String> predatorAnimals = new ArrayList<String>(Arrays.asList(new String[]{"Lion", "Wolf"})) ;
        public static final List<String> animalNames = new ArrayList<String>(Arrays.asList(new String[]{"Шарик", "Пушок","Рекс","Дружок", "Хасан", "Джульбарс"})) ;
        public static final List<String> animalCharacters = new ArrayList<String>(Arrays.asList(new String[]{"Predator", "Pat"})) ;
        public static final List<Double> animalCosts = new ArrayList<Double>(Arrays.asList(new Double[]{ 22.30, 32.40, 45.80, 15.33, 18.95})) ;

        @Override
        public HashMap<String, LocalDate> findLeapYearNames(hw5.Animal[] animals) throws invalidAnimalBirthDateException {
            SearchService ss = new SearchServiceImpl();
            HashMap<String, LocalDate> retMap = new HashMap<>();
            for (hw5.Animal animal : animals) {
                if(ss.searchService((AbstractAnimal) animal))
                    retMap.put(animal.getBreed() + animal.getName(), animal.getBirthday());
            }
            return retMap;
        }

        @Override
        public HashMap<hw5.Animal, Integer> findOlderAnimal(hw5.Animal[] animals, Integer n) {
            HashMap<hw5.Animal, Integer> retMap = new HashMap<>();
            hw5.Animal defAnimal=null;
            int maxAge = 0;
            LocalDate now =LocalDate.now();
            for (hw5.Animal animal : animals) {
                if(now.getYear()-animal.getBirthday().getYear() >= n ) retMap.put(animal,now.getYear()-animal.getBirthday().getYear());
                else
                    if(now.getYear()-animal.getBirthday().getYear() > maxAge) {
                        maxAge= now.getYear()-animal.getBirthday().getYear();
                        defAnimal = animal;
                    }
            }
            if(retMap.isEmpty()) retMap.put(defAnimal,maxAge);
            return retMap;
        }

        @Override
        public HashMap<String, Integer> findDuplicate(hw5.Animal[] animals){
            HashMap<String, Integer> retMap = new HashMap<>();
            int count;
            for (Animal animal : animals) {
                if(retMap.containsKey(animal.getBreed()))
                    count=retMap.get(animal.getBreed())+1;
                else count = 1;
                retMap.put(animal.getBreed(),count);
            }
            return retMap;
        }
    }
}