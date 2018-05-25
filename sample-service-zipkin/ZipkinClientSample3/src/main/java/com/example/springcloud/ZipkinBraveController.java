package com.example.springcloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service3")
public class ZipkinBraveController {
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public String service() throws Exception {
		Thread.sleep(300);
		return "service3";

	}
}
