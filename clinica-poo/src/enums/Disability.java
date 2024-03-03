package enums;

import java.util.stream.Stream;

public enum Disability {
    MOTOR("mo"), MENTAL("me"), VISUAL("v"), OTHER("o");
    
    @SuppressWarnings("unused")
    private String abbreviation;

    private Disability(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public static Disability fromString(String from) {
        return Stream.of(Disability.values())
            .filter(d -> d.toString().startsWith(from))
            .findFirst()
            .orElseThrow(() -> 
                new IllegalArgumentException(from)
            );
    }
}














