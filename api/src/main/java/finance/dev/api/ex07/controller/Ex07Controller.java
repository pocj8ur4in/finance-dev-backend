package finance.dev.api.ex07.controller;

import finance.dev.api.common.handler.CookieHandler;
import finance.dev.domain.ex07.dto.LoginRequest;
import finance.dev.domain.ex07.dto.RegisterRequest;
import finance.dev.domain.ex07.dto.UsernameCheckRequest;
import finance.dev.domain.ex07.entity.User;
import finance.dev.domain.ex07.entity.UserList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class Ex07Controller {
    private final UserList users;
    private final CookieHandler cookieHandler;

    @GetMapping("/ex07")
    public String ex07GET(
            @CookieValue(name = "cookie", required = false) String username,
            Model model) {
        model.addAttribute("message", "홈");

        return "ex07/index";
    }

    @GetMapping("/ex07/register")
    public String ex07RegisterGET(Model model) {
        model.addAttribute("message", "회원가입");

        return "ex07/register";
    }

    @PostMapping("/ex07/register")
    public String ex07RegisterPOST(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String passwordConfirm,
                                    Model model) {
        try {
            RegisterRequest registerRequest = new RegisterRequest(username, password, passwordConfirm);
            users.register(registerRequest);
            return "redirect:/ex07/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "ex07/register";
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return "ex07/register";
        }
    }

    @GetMapping("/ex07/login")
    public String ex07LoginGET(Model model) {
        model.addAttribute("message", "로그인");
        return "ex07/login";
    }

    @PostMapping("/ex07/login")
    public String ex07LoginPOST(LoginRequest loginRequest,
            Model model) {
        try {
            User loginUser = users.login(loginRequest);
            model.addAttribute("cookie", cookieHandler.createCookie("userId", loginUser.getId().toString(), 60 * 60 * 24));
            return "ex07/index";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "ex07/login";
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return "ex07/login";
        }
    }

    @PostMapping("/ex07/username")
    public HttpStatus ex07UsernamePOST(UsernameCheckRequest usernameCheckRequest,
                                    Model model) {
        try {
            users.checkUsername(usernameCheckRequest);
            model.addAttribute("message", "사용 가능한 아이디입니다.");
            return HttpStatus.OK;
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
