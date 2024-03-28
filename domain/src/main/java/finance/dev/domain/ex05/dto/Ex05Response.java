package finance.dev.domain.ex05.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Ex05Response {
    private final int result;

    @Builder
    public Ex05Response(int result) {
        this.result = result;
    }
}
