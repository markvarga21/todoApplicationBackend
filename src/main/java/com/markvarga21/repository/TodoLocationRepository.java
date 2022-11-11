package com.markvarga21.repository;

import com.markvarga21.entity.TodoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoLocationRepository extends JpaRepository<TodoLocation, Long> {
}
