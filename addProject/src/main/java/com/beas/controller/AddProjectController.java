package com.beas.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beas.Repo.AddProjectRepo;
import com.beas.entity.AddProjectEntity;
import com.beas.service.AddProjectService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AddProjectController {
	@Autowired
	private AddProjectService addProjectService;
	@Autowired
	private AddProjectRepo addProjectRepo;

	@PostMapping("/addProject")
	public ResponseEntity<String> CreatePremises(@RequestBody AddProjectEntity addProjectEntity) {
		return ResponseEntity.ok(addProjectService.saveProject(addProjectEntity));
	}

	@GetMapping("/allProjects")
	public ResponseEntity<List<AddProjectEntity>> getAllProject() {
		
		return new ResponseEntity<List<AddProjectEntity>>(addProjectRepo.findAllProjectsDesc(), HttpStatus.OK);

	}

	@GetMapping("/{project_id}")
	public ResponseEntity<AddProjectEntity> getAllProjectbyid(@PathVariable("project_id") String project_id) {
		System.out.println("project id "+project_id);
		Optional<AddProjectEntity> pro = addProjectRepo.findById( Integer.parseInt(project_id));
		if (pro.isPresent()) {
			return new ResponseEntity<AddProjectEntity>(pro.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<AddProjectEntity>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/updateproject")
	public ResponseEntity<AddProjectEntity> updateProject(@RequestBody AddProjectEntity entity) {
			
		entity.setCreation_date(String.valueOf(new Date()));
			return ResponseEntity.ok(addProjectRepo.save(entity));
	}

	@DeleteMapping("/projectDelete/{project_id}")
	public ResponseEntity<String> deletePremisesById(@PathVariable int project_id) {
	    try {
	        addProjectRepo.deleteById(project_id);
	        return ResponseEntity.ok("Project deleted successfully");
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.notFound().build(); 
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to delete project");
	    }
	}

}
