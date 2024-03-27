package finance.dev.domain.ex01.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Count {
    private int cnt;

    public int plusCnt() {
        return ++cnt;
    }

    public int minusCnt() {
        return --cnt;
    }
}
