package egovframework.com.ext.jstree.support.security.controller;

import com.google.gson.JsonObject;
import egovframework.com.ext.jstree.support.mvc.GenericAbstractController;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/api/jsTreeServiceFramework/security"})
public class SecurityController extends GenericAbstractController {

    @ResponseBody
    @RequestMapping(value = {"/csrf.do"}, method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView jsTreeCSRFtoJson(ModelMap model, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView("jsonView");

        CsrfToken token = (CsrfToken)request.getAttribute(CsrfToken.class.getName());

        JSONObject csrfJsonObj = new JSONObject();
        csrfJsonObj.put("_csrf_token", token.getToken());
        csrfJsonObj.put("_csrf_headerName", token.getHeaderName());
        csrfJsonObj.put("_csrf_parameterName", token.getParameterName());

        modelAndView.addObject("result", csrfJsonObj);
        return modelAndView;
    }

}
