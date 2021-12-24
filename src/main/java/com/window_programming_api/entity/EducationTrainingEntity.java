package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "education_training")
@Getter
@Setter
public class EducationTrainingEntity extends BaseEntity{

	@Id
	@Column(name="username", columnDefinition = "VARCHAR(20)")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
	
	@Column(name = "token_code", columnDefinition = "TEXT")
	private String tokenCode;
	
	@ManyToOne 
    @JoinColumn(name = "role_code", nullable=false)
    private RoleEntity role;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "picture")
	private String picture;
	
}
