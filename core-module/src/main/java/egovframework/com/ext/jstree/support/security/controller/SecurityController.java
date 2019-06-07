package egovframework.com.ext.jstree.support.security.controller;

import egovframework.com.ext.jstree.support.mvc.GenericAbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = {"/api/jsTreeServiceFramework/security"})
public class SecurityController extends GenericAbstractController {

    @RequestMapping("/csrf.do")
    public String jsTreeCSRFtoJson() {
        return "egovframework/com/ext/jstree/csrf";
    }

    @RequestMapping("/getCSRF.do")
    public ModelAndView jsTreeGetCSRFtoJson(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        String token = request.getParameter("X-CSRF-TOKEN");
        if (token == null || "".equals(token)) {
            token = request.getHeader("X-CSRF-TOKEN");
        }

        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject("result", token);
        return modelAndView;
    }

}
