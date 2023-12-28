package Date.exceptions;

public class PacienteInvalidoException extends Exception {
    public PacienteInvalidoException(){
        super();
    }

    public PacienteInvalidoException(String msg){
        super(msg);
    }
}
