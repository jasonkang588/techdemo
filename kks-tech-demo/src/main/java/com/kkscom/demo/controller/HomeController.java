package com.kkscom.demo.controller;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kkscom.demo.constant.CommonConst;
import com.kkscom.demo.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TestService testService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/conntest", method = RequestMethod.GET)
	public String connTest(Locale locale, Model model) {
		logger.info("ConnTest Start.");
		String msg = this.testService.connTest(); 
		logger.info("Result msg : " + msg);
		model.addAttribute("msg", msg);
		return "testmassage";
	}
	
	@RequestMapping(value = "/includetest", method = RequestMethod.GET)
	public String includeTest(Locale locale, Model model) {
		logger.info("includeTest Start.");
		String msg = "include test on air........"; 
		logger.info("Result msg : " + msg);
		model.addAttribute("msg", msg);
		return "include/body";
	}
	
	@RequestMapping(value = "/jqgrid", method = RequestMethod.GET)
	public String jqgrid(Locale locale, Model model) {
		logger.info("jqgrid Start.");
		return "jqwidget/grid";
	}
	
	@RequestMapping(value = "/loadtest", method = RequestMethod.GET)
	public String loadtest(Locale locale, Model model) {
		logger.info("loadtest Start.");
		return "htmlcallback/loadtest";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	
	
	
//	@RequestMapping(value = "**/favicon.ico")
//	public String favicon(Locale locale, Model model) {
//		logger.info("favicon requested.");
//		return "redirect:/resources/" + "fav.ico";
//	}
	
}
