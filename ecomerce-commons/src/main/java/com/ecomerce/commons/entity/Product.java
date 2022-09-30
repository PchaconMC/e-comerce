package com.ecomerce.commons.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="PRODUCTS")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	private String name;
	@Column(nullable = false)
	private boolean status;
	@Column(name = "change_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date changeDate;
	@Column(name = "user_update")
	private String userUpdate;
	
	private static final long serialVersionUID = 1L;
}
