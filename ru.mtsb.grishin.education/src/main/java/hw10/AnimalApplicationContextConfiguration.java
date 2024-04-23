package hw10;
import hw6.*;
import hw6.Animal;
import hw6.AnimalsRepositoryImpl;
import hw6.Predator;
import hw6.RandValues;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

@Configuration
@PropertySources(
        //{@PropertySource("file:///C:\\IdeaProjects\\mts-homework-AGrishin2\\ru.mtsb.grishin.education\\src\\main\\resources\\application.properties")})
        {@PropertySource("application.properties")})

public class AnimalApplicationContextConfiguration {
    RandValues<String> rv = new RandValues();
    RandValues<Double> rvd = new RandValues();
    Random rnd = new Random();
    LocalDate getBd() {
        return LocalDate.parse("2000-01-01").plusDays(rnd.nextInt(7300));
    }
    private static final String DIR = "src/main/resources/animals/";
    private static final String PATH = DIR+"logData.txt";
    @Value( "${animal.lion.names}")
    String[] lionNames;
    @Value( "${animal.wolf.names}")
    String[] wolfNames;
    @Value( "${animal.cat.names}")
    String[] catNames;
    @Value( "${animal.dog.names}")
    String[] dogNames;
    //@Value( "${animal.maxAnimals}")
    //int maxI;
    @Bean
    @Scope("prototype")
    public Lion getLion() {
        return new hw10.Lion(rv.listItem(List.of(lionNames)),rvd.listItem(AnimalsRepositoryImpl.animalCosts),getBd());
    }
    @Bean
    @Scope("prototype")
    public Wolf getWolf() {
        return new hw10.Wolf(rv.listItem(List.of(wolfNames)),rvd.listItem(AnimalsRepositoryImpl.animalCosts),getBd());
    }
    @Bean
    @Scope("prototype")
    public Cat getCat() {
        return new hw10.Cat(rv.listItem(List.of(catNames)),rvd.listItem(AnimalsRepositoryImpl.animalCosts),getBd());
    }
    @Bean
    @Scope("prototype")
    public Dog getDog() {
        return new hw10.Dog(rv.listItem(List.of(dogNames)),rvd.listItem(AnimalsRepositoryImpl.animalCosts),getBd());
    }

    @Bean
    @Scope("prototype")
    public Map<String, List<Animal>> createAnimals(@Value("${animal.maxAnimals}") Integer maxI) throws invalidAnimalBirthDateException {
        System.out.println("Старт сервиса createAnimals с параметром maxI="+maxI);
        Map<String, List<hw6.Animal>> animalsMap = new HashMap();
        try {
            if (Files.notExists(Path.of(DIR))) {
                Files.createDirectory(Path.of(DIR));
            }
            if (Files.exists(Path.of(PATH))) {
                Files.delete(Path.of(PATH));
            }
            Files.createFile(Path.of(PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(RandomAccessFile rAF=new RandomAccessFile(PATH, "rw");
            FileChannel channel = rAF.getChannel();) {
            ByteBuffer buffer = ByteBuffer.allocate(128);
            int i = 0;
            Animal animal = null;
            SearchServiceImpl ss = new SearchServiceImpl();
            String animalChar;
            String animalBreed;
            ArrayList<Animal> tmp;
            do {
                animalChar = (String) rv.listItem(new ArrayList(hw10.AnimalsRepositoryImpl.animalCharacters));
                if (animalChar.equals("Predator")) {
                    animalBreed = rv.listItem( hw10.AnimalsRepositoryImpl.predatorAnimals);
                    switch (animalBreed) {
                        case ("Lion"):
                            animal = getLion();
                            break;
                        case ("Wolf"):
                            animal = getWolf();
                            break;
                    }
                } else {
                    animalBreed =  rv.listItem( hw10.AnimalsRepositoryImpl.patAnimals);
                    switch (animalBreed) {
                        case ("Dog"):
                            animal = getDog();
                            break;
                        case ("Cat"):
                            animal = getCat();
                            break;
                    }
                }
                if (animalsMap.containsKey(animalBreed)) tmp = (ArrayList<Animal>) animalsMap.get(animalBreed);
                else tmp = new ArrayList<>();
                tmp.add(animal);
                animalsMap.put(animalBreed, tmp);
                System.out.println(tmp+"  ?????  "+ animalsMap.get(animalBreed));
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
                System.out.println(text);

            } while (i++ < maxI - 1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(animalsMap);
        System.out.println("Финиш сервиса createAnimals с параметром maxI="+maxI);
        return animalsMap;
    }

}
