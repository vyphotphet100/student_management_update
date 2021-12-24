package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "lecturer")
@Getter
@Setter
public class LecturerEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname", columnDefinition = "TEXT")
	private String fullname;
	
	@Column(name = "birthday")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "address", columnDefinition = "TEXT")
	private String address;
	
	@OneToMany(mappedBy = "lecturer", fetch = FetchType.EAGER)
    private List<SectionClassEntity> sectionClasses = new ArrayList<SectionClassEntity>();
	
	@Column(name = "token_code", columnDefinition = "TEXT")
	private String tokenCode;
	
	@Column(name = "picture")
	private String picture;
	
	@ManyToOne 
    @JoinColumn(name = "role_code")
    private RoleEntity role;

}
