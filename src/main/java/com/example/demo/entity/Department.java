package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	
	private long did;
	
	@Column(name="dep_name",nullable=false)
	private String depName;
	
	@Column(name="dep_head",nullable=false)
	private String depHead;
	
	/*@OneToOne(mappedBy="department")
	private Student stu;*/
	
	public Department(int did, String depName, String depHead) {
		super();
		this.did = did;
		this.depName = depName;
		this.depHead = depHead;
	}
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepHead() {
		return depHead;
	}
	public void setDepHead(String depHead) {
		this.depHead = depHead;
	}
	
	
}