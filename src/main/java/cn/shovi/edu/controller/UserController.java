package cn.shovi.edu.controller;

import cn.shovi.edu.common.bean.Tbuser;
import cn.shovi.edu.common.utils.CookieUtils;
import cn.shovi.edu.common.utils.EduResult;
import cn.shovi.edu.common.utils.JsonUtils;
import cn.shovi.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    //    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String passwd,
                        HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        EduResult result = userService.login(username, passwd);
        // login sucess
        if (result.getStatus() == 200) {
            String token = result.getData().toString();
            //如果登录成功需要把token写入cookie
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
            map.put("user", result);
            return "/index";
        } else {
            map.put("msg", "用户名或密码错误");
            return "/login";
        }
    }
//用户名已经被占用返回result,否则重定向到登录页面
    @PostMapping(value = {"/register"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public EduResult register(@RequestParam String username, @RequestParam String passwd,Model model) {
        Tbuser user = new Tbuser();
        user.setUsername(username);
        user.setPasswd(passwd);
        EduResult result = userService.register(user);
//        if (result.getStatus()==200) {
//            model.addAttribute(result);
//        }
//        return "/login";
        return result;
    }



    @PostMapping(value = "/user/check",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public  EduResult checkUser(String username){
        EduResult result = userService.checkData(username, 1);
        return result;
    }
}
