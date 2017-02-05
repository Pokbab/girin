package com.cdg.girin.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdg.girin.dao.CategoryDao;
import com.cdg.girin.dao.PostDao;
import com.cdg.girin.domain.Category;
import com.cdg.girin.domain.Post;
import com.cdg.girin.type.FormType;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostDao postDao;
	
	@Autowired
    private CategoryDao categoryDao;

	/**
	 * 글쓰기 화면 호출
	 * 
	 * @param post
	 * @return
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String form(Model model, Post post) {
		
		List<Category> categoryList = categoryDao.findAll();
		
//		Map<Integer, String> categoryMap = categoryList.stream()
//				.collect(Collectors.toMap(Category::getId, Category::getName));
		
		model.addAttribute("formType", FormType.WRITE);
		model.addAttribute("categoryMap", categoryList);
		 
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
	public String list(Model model, 
			@RequestParam(value = "category", required = false, defaultValue = "0") int categoryId,
			@PageableDefault(sort={"id"}, direction=Direction.DESC, size=2) Pageable pageable) {
		
		Page<Post> postPage;
		if (categoryId > 0) {
	        postPage = postDao.findByCategoryId(categoryId, pageable);
	    } else {
	        postPage = postDao.findAll(pageable);
	    }
		
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
