package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.data.MoverData;

@Repository
public interface MoverDataRepository extends JpaRepository<MoverData, Integer> {

}
