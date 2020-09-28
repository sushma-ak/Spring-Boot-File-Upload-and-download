package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="DOCS")
public class Doc {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String docName;
	private String docType;
	
	@Lob
	private byte[] Data;
	
	
	public Doc()
	{
		
	}


	public Doc(String docName, String docType, byte[] data) {
		
		this.docName = docName;
		this.docType = docType;
		Data = data;
	}


	public Integer getId() {
		return id;
	}


	public String getDocName() {
		return docName;
	}


	public String getDocType() {
		return docType;
	}


	public byte[] getData() {
		return Data;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public void setDocType(String docType) {
		this.docType = docType;
	}


	public void setData(byte[] data) {
		Data = data;
	}
	
	
	
}
