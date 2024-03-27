package finance.dev.api.ex04.dto;

import lombok.Builder;

public class Ex04Response {
    private final int number;

    @Builder
    public Ex04Response(int number) {
        this.number = number;
    }
}
