package com.mecitdeniz.petsAppRest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cinsiyetler")
public class Cinsiyet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String adi;
	
	public Cinsiyet() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	@Override
	public String toString() {
		return "Cinsiyet [id=" + id + ", adi=" + adi + "]";
	}
	
	
}
