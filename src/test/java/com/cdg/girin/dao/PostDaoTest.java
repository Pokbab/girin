package com.cdg.girin.dao;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdg.girin.GirinApplication;
import com.cdg.girin.domain.Category;
import com.cdg.girin.domain.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GirinApplication.class)
public class PostDaoTest {

	@Autowired
	private PostDao postDAO;
	
	@Autowired
    private CategoryDao categoryDao;
	
	@Test
	public void test() throws Exception {
		// Given
		

		// When

		// Then

	}
	
	@Test
	@Ignore
	public void testNamm() throws Exception {
		Category category = new Category();
        category.setName("카테고리1");
        category.setRegDate(new Date());
		
		categoryDao.save(category);
		
		// Given
		Post post = new Post();
		post.setTitle("dfasdf");
		post.setSubTitle("소제목");
		post.setContent("내용");
		post.setCategoryId(1);
		post.setRegDate(LocalDateTime.now());

		// When
		postDAO.save(post);
		List<Post> actual = postDAO.findAll();

		// Then
		assertNotNull(actual);
	}
}
