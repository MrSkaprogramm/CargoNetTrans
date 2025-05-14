package com.androidcargo.spring.repository;

import com.androidcargo.spring.models.work.MoverWork;
import com.androidcargo.spring.models.work.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoverWorkRepository extends JpaRepository<MoverWork, Integer>  {
    Optional<MoverWork> findByMoverWorkType(WorkType moverWorkType);
}
