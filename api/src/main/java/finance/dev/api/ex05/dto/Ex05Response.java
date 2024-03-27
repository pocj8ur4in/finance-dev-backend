package finance.dev.api.ex05.dto;

import lombok.Builder;

public class Ex05Response {
    private final int result;

    @Builder
    public Ex05Response(int result) {
        this.result = result;
    }
}
