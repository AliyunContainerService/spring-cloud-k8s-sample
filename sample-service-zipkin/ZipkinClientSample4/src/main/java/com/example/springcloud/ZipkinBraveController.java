package com.example.springcloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service4")
public class ZipkinBraveController {
	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	public String service() throws Exception {
		Thread.sleep(400);
		return "service4";

	}
}
