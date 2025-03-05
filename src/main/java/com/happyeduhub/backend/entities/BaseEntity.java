package com.happyeduhub.backend.entities;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Getter;

@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Getter(AccessLevel.PUBLIC)
	private UUID id;

	@CreationTimestamp
	@Getter(AccessLevel.PUBLIC)
	private Instant createdAt;

	@UpdateTimestamp
	@Getter(AccessLevel.PUBLIC)
	private Instant updatedAt;

	@Version
	@Getter(AccessLevel.PUBLIC)
	private int version;
}
