package com.zf.controller;

import com.zf.pojo.Status;
import com.zf.pojo.User;
import com.zf.service.UserService;
import com.zf.utils.Captchal;
import com.zf.utils.R;
import com.zf.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("index")
    public String welcome() {
        return "index";
    }

    @GetMapping("register")
    public String register() {
        return "WEB-INF/register";
    }

    @PostMapping("register")
    public @ResponseBody Status regist(User user) {
        Status status = userService.register(user);
        return status;
    }

    @GetMapping("login")
    public String login() {
        return "/WEB-INF/login";
    }

    @GetMapping("capthca")
    public void Code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Captchal.generateCaptcha(request, response);
    }

    @PostMapping("logic")
    @ResponseBody
    public R logic( String username,  String password) { ;

        if (StringUtils.isEmpty(username)){
            return R.error();
        }
        if(!StringUtils.isEmpty(username)) {
            if (username.matches(" /^[a-z0-9][\\w\\.\\-]*@[a-z0-9\\-]+(\\.[a-z]{2,5}){1,2}$/")) {
                User user = userService.loginByEmail(username);
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                Subject subject = SecurityUtils.getSubject();
                token.setRememberMe(true);
                subject.login(token);
                return R.ok();
            }
        }
            User user = userService.loginByName(username);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            token.setRememberMe(true);
            subject.login(token);
            return R.ok();
    }

    @PostMapping("checkUsername")
    public @ResponseBody R checkUsername(String username, String email) {
        User user = userService.loginByUsernameOrEmail(username, email);
        if(user!=null){
            return R.error();
        }
        return R.ok();
    }
@PostMapping("checkEmail")
    public @ResponseBody R checkEmail(String email) {
        User user = userService.loginByEmail(email);
        if(user!=null){
            return  R.error();
        }
        return R.ok();
    }
    @PostMapping("checkCode")
    public @ResponseBody R checkCode( String cap, HttpSession session){
        Integer cap1 = (Integer) session.getAttribute("captcha");
        if(cap.equalsIgnoreCase(String.valueOf(cap1)))
            return R.ok();
        return R.error();
    }
}

