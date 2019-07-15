package egovframework.com.ext.jstree.support.security.controller;

import egovframework.com.ext.jstree.support.mvc.GenericAbstractController;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/api/jsTreeServiceFramework/security"})
public class SecurityController extends GenericAbstractController {

    @ResponseBody
    @RequestMapping(value = {"/csrf.do"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView jsTreeCSRFtoJson(HttpSession session, ModelMap model, HttpServletRequest request) throws Exception {
        CsrfToken token = (CsrfToken) session.getAttribute("HttpSessionCsrfTokenRepository.CSRF_TOKEN");
        //return "egovframework/com/ext/jstree/csrf";
        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject("result", token.getToken());
        return modelAndView;
    }

}
