package com.ad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ad.entity.ScreeningFieldMaster;


@Repository
public interface ScreeningFieldMasterRepository extends JpaRepository<ScreeningFieldMaster, Long>{
	
	@Query("select s.field from ScreeningFieldMaster s where s.version=:version")
    List<String> getScreeningFieldsByVersion(@Param("version") String version);

}