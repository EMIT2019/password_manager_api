package com.emit.password_manager_api.controller;

import com.emit.password_manager_api.dto.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RequestMapping("/default")
public interface BaseController<T extends DtoEntity, ID> {
	@GetMapping("/get-all")
	ResponseEntity<List<T>> findAll();
	
	@GetMapping("/item")
	ResponseEntity<T> findById(@RequestParam("id") ID id);
	
	@PostMapping("/save")
	ResponseEntity<T> save(@RequestBody T dtoEntity);
	
	@GetMapping("/page/{page}")
    ResponseEntity<List<T>> getPage(@PathVariable Integer page);
	
	@PutMapping("/update")
	ResponseEntity<T> update(@RequestParam("id") ID id, @RequestBody T dtoEntity);
	
	@PutMapping("/remove")	
	void logicalRemove(@RequestParam("id") ID id, @RequestBody T dtoEntity);
	
	@DeleteMapping("/delete")
	void physicalRemove(@RequestParam("id") ID id);
}
