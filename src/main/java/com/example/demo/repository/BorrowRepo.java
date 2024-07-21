 package com.example.demo.repository;

import com.example.demo.entity.Borrow;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Hidden
@Repository
public interface BorrowRepo extends JpaRepository<Borrow,Integer> {


}
