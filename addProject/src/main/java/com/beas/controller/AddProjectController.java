package com.beas.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beas.Repo.AddProjectRepo;
import com.beas.entity.AddProjectEntity;
import com.beas.service.AddProjectService;

@RestController
public class AddProjectController {
	@Autowired
	AddProjectService addProjectService;
	@Autowired
	AddProjectRepo  addProjectRepo;
	
	@PostMapping("/addProject") /// Create Entity
	public String CreatePremises(@RequestBody AddProjectEntity addProjectEntity) {
		 String  msg =  addProjectService.saveProject(addProjectEntity);

		return msg;
	}

	@GetMapping("/allProjects")
	public ResponseEntity<List<AddProjectEntity>> getAllProject(){
		List<AddProjectEntity> AddProjectEntitylist = new ArrayList<>();
		addProjectRepo.findAll().forEach(AddProjectEntitylist::add);
		return new ResponseEntity<List<AddProjectEntity>>(AddProjectEntitylist,HttpStatus.OK);
		
	}
	@GetMapping("/allProject/{project_id}")
	public ResponseEntity <AddProjectEntity> getAllProjectbyid(@PathVariable long project_id){
		Optional<AddProjectEntity> pro= addProjectRepo.findById((int) project_id);
		if (pro.isPresent()) {
			return new ResponseEntity<AddProjectEntity> (pro.get(),HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<AddProjectEntity>(HttpStatus.NOT_FOUND);
		}
		
	}
	@PutMapping("/updateproject")
	public String updateProject(@RequestBody AddProjectEntity entity) {
		
		Optional<AddProjectEntity > project= addProjectRepo.findById(entity.getProject_id());
		if (project.isPresent()) {
			AddProjectEntity existingob =	project.get();
			existingob.setProject_name(entity.getProject_name());
			existingob.setProject_duration(entity.getProject_duration());
			existingob.setTeam_size(entity.getTeam_size());
			//existingob.setCreation_date(new Date());
			existingob.setCreation_date(entity.getCreation_date());
			existingob.setStart_date(entity.getStart_date());
			
			addProjectRepo.save(entity);
			return "Updated SucessFully"+entity.getProject_id();
		}
		else
			return "invalid Id  "+entity.getProject_duration();
}
	
	@DeleteMapping("/projectDelete/{project_id}")
	public String deletePremisesById(@PathVariable int project_id) {
		addProjectRepo.deleteById(project_id);
		return "Delete successfully";
	}
	
	
}
