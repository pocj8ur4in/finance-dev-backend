package finance.dev.domain.ex07.entity;

import finance.dev.domain.ex07.dto.LoginRequest;
import finance.dev.domain.ex07.dto.RegisterRequest;
import finance.dev.domain.ex07.dto.UsernameCheckRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserList {
    private final List<User> users = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return (ArrayList<User>) users;
    }

    public void register(RegisterRequest registerRequest) {
        List<String> errorMessages = new ArrayList<>();

        // 관리자 아이디 제한
        if (registerRequest.getUsername().equals("admin")) {
            errorMessages.add("사용할 수 없는 아이디입니다.");
        }

        // 아이디 중복 확인
        if (users.stream().anyMatch(user -> user.getUsername().equals(registerRequest.getUsername()))) {
            errorMessages.add("이미 존재하는 아이디입니다.");
        }

        // 아이디 유효성 검사
        if (registerRequest.getUsername().isEmpty()) {
            errorMessages.add("아이디를 입력해주세요.");
        } else if (registerRequest.getUsername().length() < 4 || registerRequest.getUsername().length() > 12) {
            errorMessages.add("아이디는 4자리 이상 12자리 이하로 입력해주세요.");
        } else if (!registerRequest.getUsername().matches("^[a-zA-Z0-9]*$")) {
            errorMessages.add("아이디는 영문 대소문자와 숫자로만 입력해주세요.");
        } else if (registerRequest.getUsername().contains(" ")) {
            errorMessages.add("아이디에 공백을 포함할 수 없습니다.");
        }

        // 비밀번호 유효성 검사
        if (registerRequest.getPassword().isEmpty()) {
            errorMessages.add("비밀번호를 입력해주세요.");
        } else if (registerRequest.getPassword().length() < 8 || registerRequest.getPassword().length() > 16) {
            errorMessages.add("비밀번호는 8자리 이상 16자리 이하로 입력해주세요.");
        } else if (!registerRequest.getPassword().matches("^[a-zA-Z0-9!@#$%^&*]*$")) {
            errorMessages.add("비밀번호는 영문 대소문자, 숫자, 특수문자 (!@#$%^&*)로만 입력해주세요.");
        } else if (registerRequest.getPassword().contains(" ")) {
            errorMessages.add("비밀번호에 공백을 포함할 수 없습니다.");
        }

        // 비밀번호 확인 공란인지 확인
        if (registerRequest.getPasswordConfirm().isEmpty()) {
            errorMessages.add("비밀번호 확인을 입력해주세요.");
        }

        // 비밀번호 확인 일치 확인
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
            errorMessages.add("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        if (!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errorMessages));
        }

        users.add(
                User.builder()
                        .id((long) (users.size() + 1))
                        .username(registerRequest.getUsername())
                        .password(registerRequest.getPassword())
                        .createdAt(LocalDate.now())
                        .build()
        );
    }

    public void checkUsername(UsernameCheckRequest usernameCheckRequest) {
        // 아이디 중복 확인 : 아이디가 이미 존재할 경우 예외 처리
        if (users.stream().anyMatch(user -> user.getUsername().equals(usernameCheckRequest.getUsername()))) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }

    public User login(LoginRequest loginRequest) {
        if (loginRequest.getUsername().isEmpty()) {
            throw new IllegalArgumentException("아이디를 입력해주세요.");
        } else if (loginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

        return users.stream()
                .filter(user -> user.getUsername().equals(loginRequest.getUsername()))
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }

    public void delete(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

    public void update(String target, String username, String password) {
        List<String> errorMessages = new ArrayList<>();

        // 관리자 아이디 제한
        if (username.equals("admin")) {
            errorMessages.add("사용할 수 없는 아이디입니다.");
        }

        // 아이디 중복 확인
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            errorMessages.add("이미 존재하는 아이디입니다.");
        }

        // 아이디 유효성 검사
        if (username.isEmpty()) {
            errorMessages.add("아이디를 입력해주세요.");
        } else if (username.length() < 4 || username.length() > 12) {
            errorMessages.add("아이디는 4자리 이상 12자리 이하로 입력해주세요.");
        } else if (!username.matches("^[a-zA-Z0-9]*$")) {
            errorMessages.add("아이디는 영문 대소문자와 숫자로만 입력해주세요.");
        } else if (username.contains(" ")) {
            errorMessages.add("아이디에 공백을 포함할 수 없습니다.");
        }

        // 비밀번호 유효성 검사
        if (password.isEmpty()) {
            errorMessages.add("비밀번호를 입력해주세요.");
        } else if (password.length() < 8 || password.length() > 16) {
            errorMessages.add("비밀번호는 8자리 이상 16자리 이하로 입력해주세요.");
        } else if (!password.matches("^[a-zA-Z0-9!@#$%^&*]*$")) {
            errorMessages.add("비밀번호는 영문 대소문자, 숫자, 특수문자 (!@#$%^&*)로만 입력해주세요.");
        } else if (password.contains(" ")) {
            errorMessages.add("비밀번호에 공백을 포함할 수 없습니다.");
        }

        if (!errorMessages.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errorMessages));
        }

        users.stream()
                .filter(user -> user.getUsername().equals(target))
                .findFirst()
                .ifPresent(user -> {
                    user.setUsername(username);
                    user.setPassword(password);
                });
    }
}
