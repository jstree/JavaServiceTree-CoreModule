package egovframework.com.ext.jstree.support.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PagesController {

    @ResponseBody
    @RequestMapping(value = {"/userpage"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView userPage() {
        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject("user", getUser());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = {"/accessdenied"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView accessDeniedPage(ModelMap model, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject("result", "Either username or password is incorrect");
        return modelAndView;
    }

    private String getUser() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
