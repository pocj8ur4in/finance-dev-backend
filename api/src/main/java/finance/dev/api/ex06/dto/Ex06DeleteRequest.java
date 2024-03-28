package finance.dev.api.ex06.dto;

import lombok.Data;

@Data
public class Ex06DeleteRequest {
    private String name;

    public Ex06DeleteRequest() {}

    public Ex06DeleteRequest(String name) {
        this.name = name;
    }
}
