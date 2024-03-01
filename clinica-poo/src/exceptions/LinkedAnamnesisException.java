package exceptions;

public class LinkedAnamnesisException extends RuntimeException {
    public LinkedAnamnesisException() {
        super("Paciente não pode ser excluído. Anamnese atrelada.");
    }

    public LinkedAnamnesisException(String message) {
        super(message);
    }
}
