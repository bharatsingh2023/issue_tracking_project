package com.beas.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.beas.entity.AddProjectEntity;

public interface AddProjectRepo extends JpaRepository<AddProjectEntity, Integer>{

	 @Query("SELECT p FROM AddProjectEntity p ORDER BY p.id DESC")
	    List<AddProjectEntity> findAllProjectsDesc();

}
