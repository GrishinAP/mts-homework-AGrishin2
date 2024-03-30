package hw6;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreateAnimalServiceImpl implements CreateAnimalService {


    public Map<String, List<Animal>> createAnimals(Integer maxI) throws invalidAnimalBirthDateException {
        int i = 0;
        LocalDate bd = LocalDate.parse("2000-01-01");
        AbstractAnimal animal = null;
        SearchServiceImpl ss = new SearchServiceImpl();
        RandValues<String> rv = new RandValues();
        RandValues<Double> rvd = new RandValues();
        String animalChar;
        String animalBreed;
        Map<String, List<Animal>> animalsMap = new HashMap();
        ArrayList<Animal> tmp;
        do {
            animalChar = (String) rv.listItem(new ArrayList(AnimalsRepositoryImpl.animalCharacters));
            if (animalChar.equals("Predator")) {
                animalBreed = rv.listItem( AnimalsRepositoryImpl.predatorAnimals);
                switch (animalBreed) {
                    case ("Lion"):
                        animal = new Lion( rv.listItem( AnimalsRepositoryImpl.animalNames), rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                    case ("Wolf"):
                        animal = new Wolf( rv.listItem( AnimalsRepositoryImpl.animalNames),  rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                }
            } else {
                animalBreed =  rv.listItem( AnimalsRepositoryImpl.patAnimals);
                switch (animalBreed) {
                    case ("Dog"):
                        animal = new Dog( rv.listItem( AnimalsRepositoryImpl.animalNames),  rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                    case ("Cat"):
                        animal = new Cat( rv.listItem( AnimalsRepositoryImpl.animalNames),  rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                }
            }
            if (animalsMap.containsKey(animalBreed)) tmp = (ArrayList<Animal>) animalsMap.get(animalBreed);
            else tmp = new ArrayList<>();
            tmp.add(animal);
            animalsMap.put(animalBreed, tmp);
            ss.searchService(animal);
            System.out.println(animal.getCharacter() + "-" + animal.getBreed() + "-" + animal.getName() + "-" + animal.getCost() + "-" + animal.getBirthday());
            //bd = bd.plusYears(1);
            bd = bd.plusMonths(4);
            bd = bd.plusDays(2);
        } while (i++ < maxI - 1);
        System.out.println(animalsMap);
        return animalsMap;
    }

    @Override
    public Map<String, List<Animal>> createAnimals() throws invalidAnimalBirthDateException {
        LocalDate bd = LocalDate.parse("2000-01-01");
        AbstractAnimal animal = null;
        SearchServiceImpl ss = new SearchServiceImpl();
        RandValues<String> rv = new RandValues();
        RandValues<Double> rvd = new RandValues();
        String animalChar;
        String animalBreed;
        Map<String, List<Animal>> animalsMap = new HashMap();
        ArrayList<Animal> tmp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            animalChar = rv.listItem(AnimalsRepositoryImpl.animalCharacters);
            if (animalChar.equals("Predator")) {
                animalBreed = rv.listItem(AnimalsRepositoryImpl.predatorAnimals);
                switch (animalBreed) {
                    case ("Lion"):
                        animal = new Lion( rv.listItem(AnimalsRepositoryImpl.animalNames), rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                    case ("Wolf"):
                        animal = new Wolf( rv.listItem(AnimalsRepositoryImpl.animalNames),  rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                }
            } else {
                animalBreed = rv.listItem(AnimalsRepositoryImpl.patAnimals);
                switch (animalBreed) {
                    case ("Dog"):
                        animal = new Dog( rv.listItem( AnimalsRepositoryImpl.animalNames), (Double) rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                    case ("Cat"):
                        animal = new Cat( rv.listItem( AnimalsRepositoryImpl.animalNames),  rvd.listItem( AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                }
            }
            if (animalsMap.containsKey(animalBreed)) tmp = (ArrayList<Animal>) animalsMap.get(animalBreed);
            else tmp = new ArrayList<>();
            tmp.add(animal);
            animalsMap.put(animalBreed, tmp);
            ss.searchService(animal);
            System.out.println(animal.getCharacter() + "-" + animal.getBreed() + "-" + animal.getName() + "-" + animal.getCost() + "-" + animal.getBirthday());
            //bd = bd.plusYears(1);
            bd = bd.plusMonths(4);
            bd = bd.plusDays(2);
        }
        System.out.println(animalsMap);
        return animalsMap;
    }

    public void createAnimalsDef() throws invalidAnimalBirthDateException {
        CreateAnimalService.super.createAnimals();
    }
}
