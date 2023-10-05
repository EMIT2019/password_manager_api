package com.emit.password_manager_api.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.emit.password_manager_api.dto.*;

public interface AuditKeywordController extends BaseController<AuditKeywordDto, Long> {
	@GetMapping("/track-keyword")
	ResponseEntity<List<AuditKeywordDto>> findByKeyword(@RequestParam("id") Long id, @RequestParam("page") Integer page);
	
	@GetMapping("/track-keyword/{page}/date")
	ResponseEntity<List<AuditKeywordDto>> findByDate(@RequestParam("date") String date, @PathVariable Integer page);
}
