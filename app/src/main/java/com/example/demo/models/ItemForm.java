package com.example.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "item")
public class ItemForm implements Serializable {
	private static final long serialVersionUID = -6647247658748349084L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@NotBlank
	@Size(max = 10)
	private String name;
	
	@PositiveOrZero
	private Integer price;

	@NotBlank
	@Size(max = 400)
	private String content;
	
	public void clear() {
		name = null;
		price = null;
		content = null;
	}
	
	public void setId(String id) {
        this.id = id;
    }
}
