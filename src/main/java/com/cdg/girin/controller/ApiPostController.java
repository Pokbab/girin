package com.cdg.girin.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdg.girin.dao.CategoryDao;
import com.cdg.girin.dao.PostDao;
import com.cdg.girin.domain.Category;
import com.cdg.girin.domain.Post;
import com.cdg.girin.service.PostService;
import com.cdg.girin.type.FormType;

@RestController
@RequestMapping("/api")
public class ApiPostController {
	@Autowired
	private PostDao postDao;
	
	@Autowired
    private CategoryDao categoryDao;
	
	@Autowired
	private PostService postService;

	/**
	 * 글쓰기 화면 호출
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public List<Category> form(Model model, Post post) {
		return categoryDao.findAll();
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

	@GetMapping("/posts")
	@ResponseBody
	public List<Post> list(Model model) {
//		return postDao.findAll();
		return postService.getPosts();
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
