package hw6;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreateAnimalServiceImpl implements CreateAnimalService {

    private static final String DIR = "src/main/resources/animals/";
    private static final String PATH = DIR+"logData.txt";

    public Map<String, List<Animal>> createAnimals(Integer maxI) throws invalidAnimalBirthDateException {
        Map<String, List<Animal>> animalsMap = new HashMap();
        try(RandomAccessFile rAF=new RandomAccessFile(PATH, "rw");
            FileChannel channel = rAF.getChannel();) {
         ByteBuffer buffer = ByteBuffer.allocate(128);
        int i = 0;
        LocalDate bd = LocalDate.parse("2000-01-01");
        AbstractAnimal animal = null;
        SearchServiceImpl ss = new SearchServiceImpl();
        RandValues<String> rv = new RandValues();
        RandValues<Double> rvd = new RandValues();
        String animalChar;
        String animalBreed;
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
            String text=i+". "+animal.getCharacter() + "-" + animal.getBreed() + "-" + animal.getName() + "-" + animal.getCost() + "-" + animal.getBirthday()+"---"+animal.getSecretInformation()+"\n";
            buffer.put(text.getBytes());
            buffer.flip();
            try {
                channel.write(buffer);
                buffer.clear();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(animal.getCharacter() + "-" + animal.getBreed() + "-" + animal.getName() + "-" + animal.getCost() + "-" + animal.getBirthday());
            System.out.println(text);
            //bd = bd.plusYears(1);
            bd = bd.plusMonths(4);
            bd = bd.plusDays(2);
        } while (i++ < maxI - 1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
