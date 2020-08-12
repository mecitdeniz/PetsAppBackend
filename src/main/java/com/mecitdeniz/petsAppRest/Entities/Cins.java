package com.mecitdeniz.petsAppRest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinsler")
public class Cins {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String ad;
	
	private int turId;

	public Cins() {
		
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

	public int getTurId() {
		return turId;
	}

	public void setTurId(int turId) {
		this.turId = turId;
	}

	@Override
	public String toString() {
		return "Cins [id=" + id + ", ad=" + ad + ", turId=" + turId + "]";
	}

	
}
