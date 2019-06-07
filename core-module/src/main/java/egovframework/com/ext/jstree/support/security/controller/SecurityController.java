package egovframework.com.ext.jstree.support.security.controller;

import egovframework.com.ext.jstree.support.mvc.GenericAbstractController;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
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

    public static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");

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

        CsrfToken sessionToken = (CsrfToken) request.getSession().getAttribute(DEFAULT_CSRF_TOKEN_ATTR_NAME);

        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject("result", sessionToken.getToken().toString());
        return modelAndView;
    }

}
