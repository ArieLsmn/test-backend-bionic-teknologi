 package com.example.demo.repository;

import com.example.demo.entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 @Repository
 public interface ReturnRepo extends JpaRepository<Return,Integer> {

 }
