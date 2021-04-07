package com.kkscom.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkscom.demo.model.SampleModel;
import com.kkscom.demo.model.json.SampleObject;

@Controller
public class RestController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//검색어 :: 스프링 컨트롤러 파라미터
	@RequestMapping(value = "/restsample/{aaa}/{bbb}", method = RequestMethod.GET)
	public @ResponseBody String jsonTest(
			@RequestParam(value="id", required=true, defaultValue="none") String id,	//get or post param id mapping
			@RequestParam long sequence, //생략 가능
			@RequestParam("attr1") String attr2, //파라미터 명과 request param id 가 다른경우
			@RequestParam Map<String, String> paramMap, //map에 몽땅 넣을수도 있음
			Model model,
			SampleModel sampleModel, //속성의 setter에 맞게 매핑됨
			@ModelAttribute("tobeChangeNotSimpleModel2") SampleModel sampleModel2, //또한 이 어노테이션을 사용하면 Null 체크 등의 검증(Validation)작업을 추가로 할 수 있다. 각 멤버변수마다 valid옵션을 줄 수가 있고, 여기서 에러가 날 경우 BindExeption이 발생한다. 출처: https://haenny.tistory.com/107 [Haenny]
			@PathVariable("aaa") String a,
			//@RequestBody SampleModel sm, //http body 메세지를 객체로 변환하여 매핑하는것이므로 post 형식 리퀘스트에만 적용가능
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		logger.info("includeTest Start.");
		String msg = "include test on air........"; 
		logger.info("Result msg : " + msg);
		model.addAttribute("msg", msg);
		return "include/body";
	}
	
	@RequestMapping(value = "/jsontest", method = RequestMethod.GET)
	@ResponseBody
	public String jsonTest(@RequestParam String msg) {
		ObjectMapper obm  = new ObjectMapper();
	    
        Map<String, Object > map = new HashMap<String, Object>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        
        try {
			return obm.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return null;
	}
	
	@RequestMapping(value = "/jsonrequest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity jsonTest2(@RequestParam String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper obm  = new ObjectMapper();
	    
		 
        /** Json문자열 -> Map */
        String jsonStr = "{ \"name\" : \"민\" , \"age\" : 28 }";
        Map<String, Object > map = new HashMap<String, Object>();
        map = obm.readValue(jsonStr, new TypeReference<Map<String, Object>>()  {});
        
        //System.out.println("jsonString -> map : " + map);
		return null;
	}
	
	@RequestMapping(value = "/jsontest2", method = RequestMethod.GET)
	@ResponseBody
	public SampleObject jsonTest3(@RequestParam String msg) {
		System.out.println("who's called me? " + msg);
		SampleObject o = new SampleObject();
		Map<String, Object > map = new HashMap<String, Object>();
		List<String> list = new ArrayList<>();
		list.add("item00");
		list.add("item01");
		map.put("itemList", list);
		o.setData(map);
		
		return o;
	}
}
