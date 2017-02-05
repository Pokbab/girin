package com.cdg.girin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdg.girin.domain.Hello;

public interface HelloDao extends JpaRepository<Hello, Integer> {

}
