package com.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ad.entity.ElementDataValue;


@Repository
public interface ElementDataValueRepository extends JpaRepository<ElementDataValue, Long>{

}