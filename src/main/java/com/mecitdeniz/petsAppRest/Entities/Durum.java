package com.mecitdeniz.petsAppRest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "durumlar")
public class Durum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String ad;
	
	public Durum() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	@Override
	public String toString() {
		return "Durum [id=" + id + ", ad=" + ad + "]";
	}
	
	
}
