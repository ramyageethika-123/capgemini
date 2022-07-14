package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int cid;
	
	@Column(name="crs_name",nullable=false)
	private String crsName;
	

}