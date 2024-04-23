package hw10;


import hw6.Animal;
import hw6.AnimalRepository;
import hw6.AnimalsRepositoryImpl;
import hw6.CreateAnimalServiceImpl;
import hw6.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HW7 {
    public static void main(String[] args) throws invalidAnimalBirthDateException {
        int animalNumber = 53;
        AnimalRepository ar = new AnimalsRepositoryImpl();
        hw6.CreateAnimalServiceImpl cAI = new CreateAnimalServiceImpl();
        Map<String, List<hw6.Animal>> animalMap = cAI.createAnimals(animalNumber);
        List<hw6.Animal> allLists = new ArrayList<>();
        for (String oneKey : animalMap.keySet()) {
            allLists.addAll(animalMap.get(oneKey));
        }
        hw6.Animal[] animalsArr = new hw6.Animal[animalNumber];
        for (int i = 0; i < animalNumber; i++){
            animalsArr[i] = allLists.get(i);
        }

        System.out.println("\nКто рождён в высокосный год");
        System.out.println(ar.findLeapYearNames(animalsArr));

        int k = 17;
        System.out.println("\nЖивотные старше " + k + " лет");
        System.out.println(ar.findOlderAnimal(animalsArr, k));

        System.out.println("\nКоличество задублированных типов животных.");
        System.out.println(ar.findDuplicate(animalsArr));

        System.out.println("\nСредний возраст животных.");
        System.out.println(ar.findAverageAge(animalsArr));

        System.out.println("\nСортированный список животных старше 5 лет и дороже средней цены.");
        List<hw6.Animal> sortAnimal = ar.findOldAndExpensive(animalsArr);
        for (hw6.Animal animal : sortAnimal) {
            System.out.println(animal.getBreed() + " " + animal.getBirthday() + " " + animal.getCost());
        }

        System.out.println("\nСортированный список имён дешовых животных.");
        List<Animal> minCosttAnimal = ar.findMinConstAnimals(animalsArr);
        System.out.println(minCosttAnimal);
        }
    }

