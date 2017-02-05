package com.cdg.girin.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdg.girin.domain.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

	public Page<Post> findByCategoryId(int categoryId, Pageable pageable);

}
