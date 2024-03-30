package hw5;


import hw6.InvalidAnimalException;

import java.time.LocalDate;
import java.util.*;


public interface CreateAnimalService {
    default Map<String,List<Animal>> createAnimals() throws invalidAnimalBirthDateException {
        int i = 0;
        LocalDate bd = LocalDate.parse("2000-01-01");
        AbstractAnimal animal=null;
        SearchServiceImpl ss = new SearchServiceImpl();
        RandValues rv = new RandValues();
        String animalChar;
        String animalBreed;
        HashMap<String,List<Animal>> animalsMap =new HashMap();
        ArrayList<Animal> tmp;
        while (i++ < 10) {
            animalChar  = (String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalCharacters) ;
            if(animalChar.equals("Predator")) {
                animalBreed = (String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.predatorAnimals);
                switch (animalBreed){
                case ("Lion"):
                    animal=new Lion((String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalNames), (Double) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalCosts), bd);
                    break;
                case ("Wolf"):
                    animal=new Wolf((String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalNames), (Double) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalCosts), bd);
                    break;
                }
            }else {
                animalBreed = (String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.patAnimals);
                switch (animalBreed) {
                    case ("Dog"):
                        animal=new Dog((String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalNames), (Double) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                    case ("Cat"):
                        animal=new Cat((String) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalNames), (Double) rv.listItem((ArrayList) InvalidAnimalException.AnimalsRepositoryImpl.animalCosts), bd);
                        break;
                }
            }
            if(animalsMap.containsKey(animalBreed)) tmp= (ArrayList<Animal>) animalsMap.get(animalBreed);
            else tmp=new ArrayList<>();
            tmp.add(animal);
            animalsMap.put(animalBreed,tmp);
            ss.searchService(animal);
            System.out.println(animal.getCharacter() + "-" + animal.getBreed() + "-" + animal.getName() + "-" + animal.getCost() + "-" + animal.getBirthday());
            //bd = bd.plusYears(1);
            bd = bd.plusMonths(4);
            bd = bd.plusDays(2);
        }
        System.out.println(animalsMap);
        return animalsMap;
    }
}