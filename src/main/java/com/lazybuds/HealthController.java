package com.lazybuds;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Meeting point of UI and services.
 * Health Controller responds test
 * @author Ritupriya 
 *
 */
@Controller 
public class HealthController {
	
    @RequestMapping("/ping")
	public @ResponseBody String ping() {
		return "pong";
	}
}
