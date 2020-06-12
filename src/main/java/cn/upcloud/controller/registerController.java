package cn.upcloud.controller;

import cn.upcloud.mapper.UserMapper;
import cn.upcloud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class registerController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/register")
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/adduser")
    public String adduer(HttpServletRequest request, Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userphone = request.getParameter("userphone");
        System.out.println(username);

        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserphone(userphone);
        User user1=userMapper.finduserbyname(username);
       if(user1!=null){
           map.put("msg","该用户名已被注册，请重新注册");
           return "/register";
       }else{
           userMapper.adduser(user);
           map.put("msg","注册成功，请登录");
           return "/login";
       }

       // map.put("msg",user);



    }
}
