package Date.exceptions;

public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException() {
		super();
	}

	public PacienteNaoEncontradoException(String msg) {
		super(msg);
	}
}
