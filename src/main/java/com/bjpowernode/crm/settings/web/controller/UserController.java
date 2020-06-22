package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.HandleFlag;
import com.bjpowernode.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * lzx
 * 2020/2/14
 */

@Controller
@RequestMapping("/settings/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String,Object> login(String loginAct, String loginPwd, String flag, HttpServletRequest request, HttpServletResponse response) throws LoginException {

        loginPwd = MD5Util.getMD5(loginPwd);

        String ip = request.getRemoteAddr();

        User user = userService.login(loginAct, loginPwd, ip);

        request.getSession().setAttribute("user", user);

        //实现十天免登陆
        if("a".equals(flag)){
            Cookie cookie1 = new Cookie("loginAct", loginAct);
            Cookie cookie2 = new Cookie("loginPwd", loginPwd);

            //设置Cookie的作用路径
            cookie1.setPath("/");
            cookie2.setPath("/");

            //设置cookie的作用时间
            cookie1.setMaxAge(60*60*20*10);
            cookie2.setMaxAge(60*60*20*10);

            response.addCookie(cookie1);
            response.addCookie(cookie2);
        }

        return HandleFlag.successTrue();
    }


    @RequestMapping("/toLogin.do")
    public String toLogin(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&& cookies.length>0){
            String loginAct=null;
            String loginPwd=null;
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("loginAct".equals(name)){
                    loginAct=cookie.getValue();
                    continue;
                }
                if("loginPwd".equals(name)){
                    loginPwd=cookie.getValue();
                }
            }

            if(loginAct!=null&&loginPwd!=null){

                String ip=request.getRemoteAddr();
                try {
                    User user = userService.login(loginAct, loginPwd, ip);

                    request.getSession().setAttribute("user", user);
                    return "redirect:/workbench/toIndex.do";
                } catch (LoginException e) {
                    return "/login";

                }

            }else {
                return "/login";
            }
        }

        return "/login";
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session, HttpServletResponse response){

        //将session销毁掉，解除浏览器跟服务器的会话状态
        session.invalidate();

        //将10天免登陆处理掉
        Cookie cookie1 = new Cookie("loginAct", null);
        Cookie cookie2 = new Cookie("loginPwd", null);

        cookie1.setPath("/");
        cookie2.setPath("/");

        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);

        response.addCookie(cookie1);
        response.addCookie(cookie2);

        return "/login";
    }
}
