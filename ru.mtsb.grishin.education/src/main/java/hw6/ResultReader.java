package hw6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ResultReader {
    private static final String DIR1 = "src/main/resources/results/";
    private static final String PATH1 = DIR1 + "findOlderAnimals.json";

    private static final String DIR2 = "src/main/resources/animals/";
    private static final String PATH2 = DIR2 + "logData.txt";

    public static void readOldAnimals() {
        if (Files.notExists(Path.of(PATH1))) {
            throw new RuntimeException("Нет файла " + PATH1 + ". :=(");
        }
        try (RandomAccessFile rAF = new RandomAccessFile(PATH1, "rw");
             FileChannel channel = rAF.getChannel();
        ) {
            File secret = new File(PATH1);
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Scanner scanner = new Scanner(secret);
            AnimalDeserializer animalDeserializer = new AnimalDeserializer(Animal.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(df);
            SimpleModule module =
                    new SimpleModule("AnimalDeserializer");
            module.addDeserializer(Animal.class, animalDeserializer);
            mapper.registerModule(module);
            mapper.findAndRegisterModules();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            while (scanner.hasNextLine()) {
                Animal animal = mapper.readValue(scanner.nextLine(), Wolf.class);
                System.out.println(animal);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /////////////////////////////
    public static void readLogData() {

        int count = 0;
        if (Files.notExists(Path.of(PATH2))) {
            throw new RuntimeException("Нет файла " + PATH2 + ". :=(");
        }
        try (RandomAccessFile rAF = new RandomAccessFile(PATH2, "rw");
             FileChannel channel = rAF.getChannel();
        ) {
            File secret = new File(PATH2);
            Scanner scanner = new Scanner(secret);
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Количество строк в файле " + PATH2 + " = " + count);
    }


}
