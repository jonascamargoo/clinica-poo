package exceptions;

public class InvalidPatientException extends RuntimeException {
    public InvalidPatientException(){
        super("Paciente invalido");
    }

    public InvalidPatientException(String msg){
        super(msg);
    }
}
