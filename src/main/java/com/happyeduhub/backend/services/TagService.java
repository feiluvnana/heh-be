package com.happyeduhub.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyeduhub.backend.entities.TagEntity;
import com.happyeduhub.backend.repositories.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;

	public List<TagEntity> readAll() {
		return tagRepository.findAll();
	}
}
