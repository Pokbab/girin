package com.cdg.girin.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdg.girin.dao.PostDao;
import com.cdg.girin.domain.Post;
import com.cdg.girin.type.FormType;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostDao postDao;

	/**
	 * 글쓰기 화면 호출
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(Model model, Post post) {
		model.addAttribute("formType", FormType.WRITE);
		return "form";
	}
	
	/**
	 * 실제로 글을 저장하는 요청
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@Valid Post post) {
		post.setRegDate(LocalDateTime.now());
		return "redirect:/post/" + postDao.save(post).getId();
	}

	@RequestMapping("/list")
	public String list(Model model, Pageable pageable) {
		Page<Post> postPage = postDao.findAll(pageable);
		model.addAttribute("postPage", postPage);
		return "list";
	}

	@RequestMapping("/{id}")
	public String view(Model model, @PathVariable int id) {
		Post post = postDao.findOne(id);
		model.addAttribute("post", post);
		return "post";
	}
	
	/**
	 * 글 수정 폼
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String editor(Model model, @PathVariable int id) {
		Post post = postDao.findOne(id);
		model.addAttribute("post", post);
		model.addAttribute("formType", FormType.EDIT);
		return "form";
	}
	
	/**
	 * 글 수정 요청
	 * 
	 * @param post
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	public String edit(@Valid Post post) {
		post.setModifyDate(LocalDateTime.now());
		return "redirect:/post/" + postDao.save(post).getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable int id) {
		postDao.delete(id);
		return "redirect:/post/list";
	}
}
