package egovframework.com.ext.jstree.support.security.controller;

import egovframework.com.ext.jstree.support.mvc.GenericAbstractController;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = {"/api/jsTreeServiceFramework/security"})
public class SecurityCSRFController extends GenericAbstractController {

    @RequestMapping("/getCSRF")
    public ModelAndView jsTreeCSRFtoJson(HttpServletRequest request) {

        CsrfToken token = (CsrfToken)request.getSession().getAttribute("HttpSessionCsrfTokenRepository.CSRF_TOKEN");
        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject(token);
        return modelAndView;

    }

}