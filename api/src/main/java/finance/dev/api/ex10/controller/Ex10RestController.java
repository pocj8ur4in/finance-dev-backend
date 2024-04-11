package finance.dev.api.ex10.controller;

import finance.dev.api.common.handler.CookieHandler;
import finance.dev.domain.ex07.dto.LoginRequest;
import finance.dev.domain.ex07.dto.RegisterRequest;
import finance.dev.domain.ex07.dto.UsernameCheckRequest;
import finance.dev.domain.ex07.entity.User;
import finance.dev.domain.ex07.entity.UserList;
import finance.dev.domain.ex09.dto.AdminUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/v1/api/ex10")
@RequiredArgsConstructor
@Tag(name = "ex10", description = "회원가입, 로그인, 아이디 중복 확인, 관리자 관련 기능을 수행합니다.")
public class Ex10RestController {
    private final UserList users;
    private final CookieHandler cookieHandler;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입을 수행합니다.")
    @Parameter(name = "registerRequest", description = "회원가입 정보", required = true)
    public ResponseEntity<Void> ex07RegisterPOST(@RequestBody RegisterRequest registerRequest) {
        try {
            users.register(registerRequest);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인을 수행합니다.")
    @Parameter(name = "loginRequest", description = "로그인 정보", required = true)
    public ResponseEntity<Void> ex07LoginPOST(@RequestBody LoginRequest loginRequest) {
        try {
            User user = users.login(loginRequest);
            Cookie cookie = cookieHandler.createCookie(
                    "user",
                    user.getUsername(),
                    60 * 60 * 24 * 30
            );
            return ResponseEntity.ok().header("Cookie", cookie.toString()).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/username")
    @Operation(summary = "아이디 중복 확인", description = "아이디 중복 확인을 수행합니다.")
    @Parameter(name = "usernameCheckRequest", description = "아이디 중복 확인 정보", required = true)
    public ResponseEntity<Void> ex07UsernamePOST(@RequestBody UsernameCheckRequest usernameCheckRequest) {
        try {
            users.checkUsername(usernameCheckRequest);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/read")
    public ResponseEntity<ArrayList<User>> readUsers() {
        return new ResponseEntity<>(users.getUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        try {
            users.delete(username);
            return new ResponseEntity<>("사용자 삭제가 성공적으로 완료되었습니다.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody AdminUpdateRequest updateUserRequest) {
        try {
            users.update(updateUserRequest.getTarget(), updateUserRequest.getUsername(), updateUserRequest.getPassword());
            return new ResponseEntity<>("사용자 정보가 성공적으로 수정되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}