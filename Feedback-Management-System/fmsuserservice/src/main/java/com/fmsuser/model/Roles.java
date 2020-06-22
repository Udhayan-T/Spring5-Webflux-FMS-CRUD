package com.fmsuser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

	@Id
	@Column("role_type")
	private String roleType;
}
