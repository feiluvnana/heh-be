package com.happyeduhub.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyeduhub.backend.entities.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, UUID> {
}
