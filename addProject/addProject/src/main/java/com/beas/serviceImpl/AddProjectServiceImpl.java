package com.beas.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beas.Repo.AddProjectRepo;
import com.beas.entity.AddProjectEntity;
import com.beas.service.AddProjectService;
@Service
public class AddProjectServiceImpl implements AddProjectService{

	@Autowired
    private AddProjectRepo projectRepository;
	
	public String saveProject(AddProjectEntity addProjectEntity) {
		addProjectEntity.setCreation_date(String.valueOf(new Date()));
		 return projectRepository.save(addProjectEntity).getProject_name();	
		
	}

}
