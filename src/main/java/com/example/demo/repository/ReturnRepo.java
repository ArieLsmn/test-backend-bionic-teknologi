 package com.example.demo.repository;

import com.example.demo.entity.Return;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Hidden
@Repository
public interface ReturnRepo extends JpaRepository<Return,Integer> {

  @Query(nativeQuery = true, value="SELECT COALESCE(SUM(quantity),0) FROM return WHERE borrow_id = %:borrowid%")
  int getReturnQuantitySumByBorrowId(@Param("borrowid") int id);
 }
