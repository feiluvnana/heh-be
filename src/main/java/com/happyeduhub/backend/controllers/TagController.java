package com.happyeduhub.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyeduhub.backend.entities.TagEntity;
import com.happyeduhub.backend.services.TagService;

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
