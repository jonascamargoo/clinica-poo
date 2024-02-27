package enums;

import java.util.Arrays;

public enum Sex {
	MALE("m"), FEMALE("f"), INTERSEX("i");
	
	private String sex;

	public String getSexo() {
		return this.sex;
	}

	private Sex(String sex) {
		this.sex = sex;
	}

    public static Sex fromString(String from) {
        return Arrays.stream(Sex.values())
                .filter(v -> v.toString().startsWith(from))
                .findFirst()
                .orElseThrow(() -> 
					new IllegalArgumentException(from)
				);
    }


}


