package Model;

public enum Sexo {
	MASCULINO("m"), FEMININO("f"), INTERSEXO("i");

	private String sexo;

	public String getSexo() {
		return this.sexo;
	}

	private Sexo(String s) {
		this.sexo = s;
	}

	public static Sexo fromString(String from) {
		for (Sexo s : Sexo.values()) {
			if (s.toString().startsWith(from)) {
				return s;
			}
		}

		throw new IllegalArgumentException(from);
	}
}