package exceptions;

public class ReadAnamnesisException extends RuntimeException {
    public ReadAnamnesisException() {
        super();
    }

    public ReadAnamnesisException(String message) {
        super("Falha ao ler anamnese");
    }
}
