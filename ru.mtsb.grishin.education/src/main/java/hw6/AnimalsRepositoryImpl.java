package hw6;

import java.text.Collator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AnimalsRepositoryImpl implements AnimalRepository {
    public static final List<String> patAnimals = Arrays.asList("Dog", "Cat");
    public static final List<String> predatorAnimals = Arrays.asList("Lion", "Wolf");
    public static final List<String> animalNames = Arrays.asList("Шарик", "Пушок", "Рекс", "Дружок", "Хасан", "Джульбарс");
    public static final List<String> animalCharacters = Arrays.asList("Predator", "Pat");
    public static final List<Double> animalCosts = Arrays.asList(22.30, 32.40, 45.80, 15.33, 18.95);

    @Override
    public HashMap<String, LocalDate> findLeapYearNames(Animal[] animals) throws invalidAnimalBirthDateException {
        SearchService ss = new SearchServiceImpl();
        //HashMap<String, LocalDate> retMap = new HashMap<>();
        Map<String, LocalDate> map = Arrays.stream(animals)
                .filter(a -> a.getBirthday().isLeapYear())
                .collect(Collectors.toMap(a -> a.getBreed() + a.getName() + String.valueOf(a.getBirthday().getDayOfYear()), Animal::getBirthday));//                        + Animal::getName(), Animal::getBirthday()));
        return (HashMap<String, LocalDate>) map;
    }

    @Override
    public HashMap<Animal, Integer> findOlderAnimal(Animal[] animals, Integer n) {
        //HashMap<Animal, Integer> retMap = new HashMap<>();
        Animal defAnimal = null;
        int maxAge = 0;
        int nowYear = LocalDate.now().getYear();
        for (Animal animal : animals) {
            if (nowYear - animal.getBirthday().getYear() > maxAge) maxAge = nowYear - animal.getBirthday().getYear();
        }
        Map<Animal, Integer> map = Arrays.stream(animals).filter(a -> nowYear - a.getBirthday().getYear() > n)
                .collect(Collectors.toMap(a -> a, a -> nowYear - a.getBirthday().getYear()));
        if (map.isEmpty()) {
            map.put(defAnimal, maxAge);
        }
        return (HashMap<Animal, Integer>) map;
    }

    @Override
    public HashMap<String, List<Animal>> findDuplicate(Animal[] animals) {
        HashMap<String, Integer> retMap = new HashMap<>();
        int count;
        Map<String, List<Animal>> map = Arrays.stream(animals).collect(Collectors.groupingBy(Animal::getBreed));

        return (HashMap<String, List<Animal>>) map;
    }

    public Double findAverageAge(Animal[] animals) {
        int nowYear = LocalDate.now().getYear();
        DoubleSummaryStatistics dss = Arrays.stream(animals).map(a -> nowYear - a.getBirthday().getYear())
                .collect(Collectors.summarizingDouble(d -> d));
        Double avrAge = dss.getAverage();
        System.out.println("Средний возраст животных составляет " + avrAge + " лет.");
        return avrAge;
    }

    public List<Animal> findOldAndExpensive(Animal[] animals) {
        DoubleSummaryStatistics dss = Arrays.stream(animals).collect(Collectors.summarizingDouble(Animal::getCost));
        Double avrCost = dss.getAverage();
        System.out.println("\nСредняя цена животных = " + avrCost);
        int nowYear = LocalDate.now().getYear();
        List list = Arrays.stream(animals).filter(a -> nowYear - a.getBirthday().getYear() > 5 && a.getCost() > avrCost)
                .sorted(new MyDateComparator())
                .collect(Collectors.toList());
        return list;
    }

    public List<Animal> findMinConstAnimals(Animal[] animals) {
        Collator ruCollator = Collator.getInstance(new Locale("ru", "RU"));
        List list = Arrays.stream(animals).sorted(Comparator.comparingDouble(Animal::getCost))
                .limit(3)
                .map(a -> a.getName())
                .sorted(ruCollator::compare)
                //.sorted(Collections.reverseOrder(ruCollator::compare))
                .collect(Collectors.toList());
        return list;
    }
}

class MyDateComparator implements Comparator<Animal> {

    public int compare(Animal a, Animal b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}
