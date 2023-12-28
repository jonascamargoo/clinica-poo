package Model;

public enum Deficiencia {
    MOTORA("mo"), MENTAL("me"), VISUAL("v"), OUTRO("o");
    private String d;

    private Deficiencia(String d) {
		this.d = d;
	}

	public static Deficiencia fromString(String from) {
		for (Deficiencia d : Deficiencia.values()) {
			if (d.toString().startsWith(from)) {
				return d;
			}
		}

		throw new IllegalArgumentException(from);
	}
}
