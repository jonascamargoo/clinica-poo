package exceptions;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException() {
        super("Paciente não encontrado");
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}
