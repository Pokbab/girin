package com.cdg.girin.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cdg.girin.dao.PostDao;
import com.cdg.girin.domain.Post;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {
	
	@InjectMocks
	private PostServiceImpl postService;
	
	@Mock
	private PostDao postDao;
	
	@Test
	public void testGetPosts() throws Exception {
		// Given
		List<Post> postList = new ArrayList<>();
		Post post = new Post();
		post.setId(1);
		post.setContent("컨텐츠");
		postList.add(post);
		
		when(postDao.findAll()).thenReturn(postList);

		// When
		List<Post> actual = postService.getPosts();

		// Then
		assertNotNull(actual);
		assertEquals(1, actual.get(0).getId());
		assertEquals("컨텐츠", actual.get(0).getContent());
		verify(postDao, times(1)).findAll();
	}
	
	@Test
	public void testGetPost_성공() throws Exception {
		// Given
		int id = 1;
		
		Post post = new Post();
		post.setId(id);
		post.setContent("컨텐츠");
		
		when(postDao.findOne(anyInt())).thenReturn(post);

		// When
		Post actual = postService.getPost(id);

		// Then
		assertNotNull(actual);
		assertEquals(post, actual);
	}
	
	@Test(expected = Exception.class)
	public void testGetPost_ID값이_0이면_에러() throws Exception {
		// Given
		int id = 0;
		
		// When
		postService.getPost(id);
	}
}
