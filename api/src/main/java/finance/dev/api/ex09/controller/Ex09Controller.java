package finance.dev.api.ex09.controller;

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
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class Ex09Controller {
    private final UserList users;
    private final CookieHandler cookieHandler;

    @GetMapping("/ex09")
    public String ex09GET(
            @CookieValue(name = "cookie", required = false) String username,
            Model model) {
        model.addAttribute("message", "홈");

        return "ex09/index";
    }

    @GetMapping("/ex09/register")
    public String ex09RegisterGET(Model model) {
        model.addAttribute("message", "회원가입");

        return "ex09/register";
    }

    @PostMapping("/ex09/register")
    public String ex09RegisterPOST(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String passwordConfirm,
                                    Model model) {
        try {
            RegisterRequest registerRequest = new RegisterRequest(username, password, passwordConfirm);
            if (registerRequest.getUsername().equals("admin")) {
                throw new IllegalArgumentException("사용할 수 없는 아이디입니다.");
            }

            users.register(registerRequest);

            return "redirect:/ex09/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "ex09/register";
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return "ex09/register";
        }
    }

    @GetMapping("/ex09/login")
    public String ex09LoginGET(Model model) {
        model.addAttribute("message", "로그인");
        return "ex09/login";
    }

    @PostMapping("/ex09/login")
    public String ex09LoginPOST(LoginRequest loginRequest,
            Model model) {
        try {
            if (loginRequest.getUsername().equals("admin")) {
                if (!loginRequest.getPassword().equals("1234")) {
                    throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
                }
                return "redirect:/ex09/admin";
            } else {
                User loginUser = users.login(loginRequest);

                model.addAttribute("cookie", cookieHandler.createCookie("userId", loginUser.getId().toString(), 60 * 60 * 24));
                return "ex09/index";
            }

        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "ex09/login";
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return "ex09/login";
        }
    }

    @PostMapping("/ex09/username")
    public HttpStatus ex09UsernamePOST(UsernameCheckRequest usernameCheckRequest,
                                    Model model) {
        try {
            if (usernameCheckRequest.getUsername().equals("admin")) {
                throw new IllegalArgumentException("사용할 수 없는 아이디입니다.");
            }

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

    @GetMapping("/ex09/admin")
    public String ex09AdminGET(Model model) {
        model.addAttribute("message", "관리자 페이지");
        model.addAttribute("members", users.getUsers());

        return "ex09/admin";
    }

    @GetMapping("/ex09/delete/{username}")
    public String ex09DeletePOST(@PathVariable("username") String username,
                                    Model model) {
        try {
            users.delete(username);
            return "redirect:/ex09/admin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/ex09/admin";
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return "redirect:/ex09/admin";
        }
    }

    @PostMapping("/ex09/update/{username}")
    public String ex09UpdatePOST(@PathVariable("username") String target,
                                    @RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    Model model) {
        try {
            users.update(target, username, password);
            return "redirect:/ex09/admin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/ex09/admin";
        } catch (Exception e) {
            model.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
            return "redirect:/ex09/admin";
        }
    }
}
