package com.happyeduhub.backend.controller;

import com.happyeduhub.backend.entity.TagEntity;
import com.happyeduhub.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
	@Autowired
	private TagService tagService;

	@GetMapping("")
	public ResponseEntity<List<TagEntity>> helloWorld() {
		return ResponseEntity.ok(tagService.readAll());
	}
}
