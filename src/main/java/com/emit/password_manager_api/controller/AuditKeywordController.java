package com.emit.password_manager_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.emit.password_manager_api.dto.*;
import com.emit.password_manager_api.model.Keyword;

public interface AuditKeywordController extends BaseController<AuditKeywordDto, Long> {
	@GetMapping("/track-keyword")
	ResponseEntity<List<AuditKeywordDto>> findByKeyword(@RequestParam("id") Long id);
}
