package com.happyeduhub.backend.service;

import com.happyeduhub.backend.entity.TagEntity;
import com.happyeduhub.backend.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;

	public List<TagEntity> readAll() {
		return tagRepository.findAll();
	}
}
