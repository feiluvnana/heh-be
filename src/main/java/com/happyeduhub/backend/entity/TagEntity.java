package com.happyeduhub.backend.entity;

import com.happyeduhub.backend.core.enums.TagType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private TagType type;

	@Column(nullable = false)
	private String fragment;
}
