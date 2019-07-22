package egovframework.com.ext.jstree.support.security.controller;

import egovframework.com.ext.jstree.support.security.dto.LocalUser;
import egovframework.com.ext.jstree.support.security.dto.UserRegistrationForm;
import egovframework.com.ext.jstree.support.security.exception.UserAlreadyExistAuthenticationException;
import egovframework.com.ext.jstree.support.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ModelAndView registerUser(@RequestBody UserRegistrationForm registrationForm) throws UserAlreadyExistAuthenticationException {

        if (registrationForm.getUserId() == null) {
            registrationForm.setUserId(registrationForm.getUserId());
        }

        LocalUser localUser = (LocalUser) userService.registerNewUser(registrationForm);

        ModelAndView modelAndView = new ModelAndView("jsonView");
        modelAndView.addObject("result", "add user success");
        return modelAndView;
    }
}
