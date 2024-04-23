package hw10;

import hw6.RandValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface Animal {
    String DIR = "src/main/resources/secretStore/";
    String PATH = DIR+"secretInformation.txt";
    String character    = "";
    String breed        = "";
    String name         = "";
    Double cost         = 0.0;
    LocalDate birthday  = null;
    String secretInformation = "";
     String getBreed();
     String getName();
     Double getCost();
     String getCharacter();
     LocalDate getBirthday();

     default String getSecretInformation(){
         try {
             if(Files.notExists(Path.of(DIR))) {
                 Files.createDirectory(Path.of(DIR));
             }
             if(Files.notExists(Path.of(PATH))) {
                 Files.copy(Path.of("src/main/java/HW6/secretInformation.txt"), Path.of(PATH));
                 }
             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

          String res="";
          List<String> list=new ArrayList<>();
          RandValues<String> rv=new RandValues<>();
         try(RandomAccessFile rAF=new RandomAccessFile(PATH, "rw");
             FileChannel channel = rAF.getChannel();
             ) {
             File secret = new File(PATH);
             Scanner scanner = new Scanner(secret);
             while(scanner.hasNextLine()){
                 list.add(scanner.nextLine());
             }
             res=rv.listItem(list);

          } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
          } catch (IOException e) {
               throw new RuntimeException(e);
          }

          return res;
     };
    void setBreed(String breed);
    void setName(String name);
    void setCost(Double cost);
    void setCharacter(String character);
    void setBirthday(LocalDate localDate);
    void setSecretInformation(String secret);

}
