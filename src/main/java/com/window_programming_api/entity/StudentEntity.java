package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
public class StudentEntity extends BaseEntity{

	@Id
	@Column(name="id", columnDefinition = "VARCHAR(20)")
	private String id;
	
	@Column(name = "first_name", columnDefinition = "TEXT")
	private String firstName;
	
	@Column(name = "last_name", columnDefinition = "TEXT")
	private String lastName;
	
	@Column(name = "fullname", columnDefinition = "TEXT")
	private String fullname;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "start_year")
	private Integer startYear;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
	
	@Column(name = "picture", columnDefinition = "LONGTEXT")
	private String picture;
	
	@Column(name = "token_code", columnDefinition = "TEXT")
	private String tokenCode;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<RegisterEntity> registers = new ArrayList<RegisterEntity>();
	
	@ManyToOne 
    @JoinColumn(name = "role_code")
    private RoleEntity role;
	
}
