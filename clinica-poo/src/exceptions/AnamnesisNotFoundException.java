package exceptions;

public class AnamnesisNotFoundException extends RuntimeException {
    public AnamnesisNotFoundException() {
        super("Anamnese não encontrada");
    }

    public AnamnesisNotFoundException(String message) {
        super(message);
    }
}
