package org.jeecg.modules.controller;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.entity.JeecgHelloEntity;
import org.jeecg.modules.service.IJeecgHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/test/jeecg")
@Slf4j
public class JeecgHelloController {

	@Autowired
	private IJeecgHelloService jeecgHelloService;

	@GetMapping(value = "/helloTest")
	public Result<String> queryPageList() {
		return jeecgHelloService.hello();
	}

}
