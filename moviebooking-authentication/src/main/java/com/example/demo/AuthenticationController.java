package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	static HashMap<String,String> users = new HashMap<String,String>();

	static {
		users.put("ram","potheneni");
		users.put("mahesh","ghattamaneni");
		users.put("charan","kodidela");
	}	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public boolean authenticateUser(@RequestBody User user) {
		logger.info("at begining - com.example.demo.AuthenticationController.authenticateUser(User)");
		boolean result = false;
		for (Map.Entry<String,String> entry : users.entrySet()) {  
            if((entry.getKey().equals(user.getUserName())) && (entry.getValue().equals(user.getPassword()))) {
            	result = true;
            }
    	}
		logger.info("at ending - com.example.demo.AuthenticationController.authenticateUser(User)");
		return result;
	}
	
}
