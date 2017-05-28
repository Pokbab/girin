package com.cdg.girin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdg.girin.dao.PostDao;
import com.cdg.girin.domain.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;
	
	@Override
	public List<Post> getPosts() {
		return postDao.findAll();
	}
	
	public Post getPost(int id) throws Exception {
		if (id == 0) {
			throw new Exception("id는 0이상의 숫자가 필요!!");
		}
		
		return postDao.findOne(id);
	}

}
