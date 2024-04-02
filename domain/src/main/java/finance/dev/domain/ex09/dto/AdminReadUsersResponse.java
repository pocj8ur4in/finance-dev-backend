package finance.dev.domain.ex09.dto;

import finance.dev.domain.ex07.entity.User;
import lombok.Data;

import java.util.ArrayList;

@Data
public class AdminReadUsersResponse {
    private ArrayList<User> users;

    public AdminReadUsersResponse(ArrayList<User> users) {
        this.users = users;
    }
}
