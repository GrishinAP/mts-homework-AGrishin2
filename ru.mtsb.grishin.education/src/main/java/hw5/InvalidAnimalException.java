package hw5;


public class InvalidAnimalException extends RuntimeException{
    public InvalidAnimalException(String message){
        super(message);
    }
}