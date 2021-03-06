package cn.edu.zucc.graduationproject.Controller;

import cn.edu.zucc.graduationproject.Service.Codeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("test")
public class ElmOrderController {
    @Autowired
    Codeservice codeservice;
    /**
     * 饿了么授权回调在此处响应，主要是获取授权码code
     */
    @GetMapping("/elm")
    public Object doGet(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws ServletException, IOException {

//        log.info("[请求错误]");
        String method=request.getParameter("method");
        //响应授权回调
        if("auth_back".equals(method)){
            String code=request.getParameter("code");
            String state=request.getParameter("state");
            codeservice.updatecode(code);
            map.put("codenum",code);
            System.out.println(code);
        }
        return "code";

    }
}
