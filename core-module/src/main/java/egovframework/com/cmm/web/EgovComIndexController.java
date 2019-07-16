package egovframework.com.cmm.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EgovComIndexController implements ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovComIndexController.class);

	public void afterPropertiesSet() throws Exception {}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		LOGGER.info("EgovComIndexController setApplicationContext method has called!");
	}

	@ResponseBody
	@RequestMapping(value = "/index.do")
	public ModelAndView index(ModelMap model, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.addObject("result", "jsTree Service Framework is not support frontside page");
		return modelAndView;
	}

}
