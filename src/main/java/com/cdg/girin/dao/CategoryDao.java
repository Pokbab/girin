package com.cdg.girin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdg.girin.domain.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

}
