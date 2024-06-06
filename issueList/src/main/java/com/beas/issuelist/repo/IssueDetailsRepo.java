package com.beas.issuelist.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.beas.issuelist.entity.IssueDetailsEntity;

public interface IssueDetailsRepo extends JpaRepository<IssueDetailsEntity, Integer> {

	@Query(value = "select ise from IssueDetailsEntity ise where ise.projectid=:projectid")
	public List<IssueDetailsEntity> getallissue(@Param("projectid") String projectid);
}
