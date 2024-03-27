package finance.dev.api.ex04.dto;

import lombok.Builder;

public class Ex04Reponse {
    private final int number;

    @Builder
    public Ex04Reponse(int number) {
        this.number = number;
    }
}
