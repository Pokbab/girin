package com.cdg.girin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdg.girin.dao.HelloDao;
import com.cdg.girin.domain.Hello;

@RestController
public class ApiController {

	@Autowired
	private HelloDao helloDao;

	@RequestMapping("/add")
	public Hello add(Hello hello) {

		hello = helloDao.save(hello);

		return hello;
	}

	@RequestMapping("/list")
	public List<Hello> list(Model model) {

		List<Hello> helloList = helloDao.findAll();

		return helloList;
	}

	@RequestMapping("/")
	public String index() {
		return "hello world";
	}
}
