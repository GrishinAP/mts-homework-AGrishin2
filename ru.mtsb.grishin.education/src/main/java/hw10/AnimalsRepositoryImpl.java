package hw10;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hw6.Animal;
import hw6.invalidAnimalBirthDateException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class AnimalsRepositoryImpl implements AnimalRepository {
    public static final List<String> patAnimals = Arrays.asList("Dog", "Cat");
    public static final List<String> predatorAnimals = Arrays.asList("Lion", "Wolf");
    public static final List<String> animalNames = Arrays.asList("Шарик", "Пушок", "Рекс", "Дружок", "Хасан", "Джульбарс");
    public static final List<String> animalCharacters = Arrays.asList("Predator", "Pat");
    public static final List<Double> animalCosts = Arrays.asList(22.30, 32.40, 45.80, 15.33, 18.95);

    private static final String DIR = "src/main/resources/results/";
    private static final String PATH = DIR + "findOlderAnimals.json";

    public final static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");


    @PostConstruct
    private void postConstruct() {

    }

    @Override
    public HashMap<String, LocalDate> findLeapYearNames(Animal[] animals) throws invalidAnimalBirthDateException {
        SearchService ss = new SearchServiceImpl();
        //HashMap<String, LocalDate> retMap = new HashMap<>();
        Map<String, LocalDate> map = Arrays.stream(animals)
                .filter(a -> a.getBirthday().isLeapYear())
                .collect(Collectors.toMap(a -> a.getBreed() + a.getName() + String.valueOf(a.getBirthday().getDayOfYear()), hw6.Animal::getBirthday));//                        + Animal::getName(), Animal::getBirthday()));
        return (HashMap<String, LocalDate>) map;
    }

    @Override
    public HashMap<hw6.Animal, Integer> findOlderAnimal(hw6.Animal[] animals, Integer n) {
        //HashMap<Animal, Integer> retMap = new HashMap<>();
        hw6.Animal defAnimal = null;
        int maxAge = 0;
        int nowYear = LocalDate.now().getYear();
        for (hw6.Animal animal : animals) {
            if (nowYear - animal.getBirthday().getYear() > maxAge) maxAge = nowYear - animal.getBirthday().getYear();
        }
        Map<hw6.Animal, Integer> map = Arrays.stream(animals).filter(a -> nowYear - a.getBirthday().getYear() > n)
                .collect(Collectors.toMap(a -> a, a -> nowYear - a.getBirthday().getYear()));
        if (map.isEmpty()) {
            map.put(defAnimal, maxAge);
        }
        AnimalSerializer animalSerializer = new AnimalSerializer(hw6.Animal.class);
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("AnimalSerializer");
        module.addSerializer(hw6.Animal.class, animalSerializer);
        mapper.registerModule(module);
        //mapper.setDateFormat(df);
        String json = null;
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

        try (RandomAccessFile rAF = new RandomAccessFile(PATH, "rw");
             FileChannel channel = rAF.getChannel();) {
            ByteBuffer buffer = ByteBuffer.allocate(256);
            buffer.flip();

            System.out.println(mapper.writeValueAsString(map));
            //final String FIND = "secretInfomation\":";
            String tmp = "";
            for (hw6.Animal mapItem : map.keySet()) {
                json = mapper.writeValueAsString(mapItem) + "\n";
//                tmp = json.substring(json.indexOf(FIND) + FIND.length());
//                tmp = tmp.substring(0, tmp.indexOf("\"}") + 1);
//                json = json.replace(tmp, Base64.getEncoder().encodeToString(tmp.getBytes()));
                buffer.clear();
                buffer.put(json.getBytes());
                buffer.flip();
                channel.write(buffer);

                System.out.println(json);
            }

        } catch (JsonProcessingException | FileNotFoundException e) {
            //} catch (Exception e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
        return (HashMap<hw6.Animal, Integer>) map;
    }

    @Override
    public HashMap<String, List<hw6.Animal>> findDuplicate(hw6.Animal[] animals) {
        HashMap<String, Integer> retMap = new HashMap<>();
        int count;
        Map<String, List<hw6.Animal>> map = Arrays.stream(animals).collect(Collectors.groupingBy(hw6.Animal::getBreed));

        return (HashMap<String, List<hw6.Animal>>) map;
    }

    public Double findAverageAge(hw6.Animal[] animals) {
        int nowYear = LocalDate.now().getYear();
        DoubleSummaryStatistics dss = Arrays.stream(animals).map(a -> nowYear - a.getBirthday().getYear())
                .collect(Collectors.summarizingDouble(d -> d));
        Double avrAge = dss.getAverage();
        System.out.println("Средний возраст животных составляет " + avrAge + " лет.");
        return avrAge;
    }

    public List<hw6.Animal> findOldAndExpensive(hw6.Animal[] animals) {
        DoubleSummaryStatistics dss = Arrays.stream(animals).collect(Collectors.summarizingDouble(hw6.Animal::getCost));
        Double avrCost = dss.getAverage();
        System.out.println("\nСредняя цена животных = " + avrCost);
        int nowYear = LocalDate.now().getYear();
        List list = Arrays.stream(animals).filter(a -> nowYear - a.getBirthday().getYear() > 5 && a.getCost() > avrCost)
                .sorted(new MyDateComparator())
                .collect(Collectors.toList());
        return list;
    }

    public List<hw6.Animal> findMinConstAnimals(hw6.Animal[] animals) {
        Collator ruCollator = Collator.getInstance(new Locale("ru", "RU"));
        List list = Arrays.stream(animals).sorted(Comparator.comparingDouble(hw6.Animal::getCost))
                .limit(3)
                .map(a -> a.getName())
                .sorted(ruCollator::compare)
                //.sorted(Collections.reverseOrder(ruCollator::compare))
                .collect(Collectors.toList());
        return list;
    }
}

class MyDateComparator implements Comparator<hw6.Animal> {

    public int compare(hw6.Animal a, Animal b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }

}


