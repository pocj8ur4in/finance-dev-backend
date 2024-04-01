package finance.dev.api.ex08.controller;

import finance.dev.api.common.handler.CookieHandler;
import finance.dev.domain.ex07.dto.LoginRequest;
import finance.dev.domain.ex07.dto.RegisterRequest;
import finance.dev.domain.ex07.dto.UsernameCheckRequest;
import finance.dev.domain.ex07.entity.User;
import finance.dev.domain.ex07.entity.UserList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/ex08")
@RequiredArgsConstructor
@Tag(name = "ex08", description = "회원가입, 로그인, 아이디 중복 확인을 수행합니다.")
public class Ex08RestController {
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
}
