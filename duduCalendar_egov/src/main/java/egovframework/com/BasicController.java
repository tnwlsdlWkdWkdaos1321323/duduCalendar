package egovframework.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
	
	@RequestMapping(value = "/duduDaily.do")
	public String basic() throws Exception{
		return "screen";
	}
	
}
