package com.mecitdeniz.petsAppRest.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "iller")
public class Il {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String ad;
	
	@OneToMany
	@JoinColumn(name="ilId", insertable = false ,updatable = false)
	private List<Ilce> ilceler;
	
	
	public Il() {
		
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
	
	

	public List<Ilce> getIlceler() {
		return ilceler;
	}

	public void setIlceler(List<Ilce> ilceler) {
		this.ilceler = ilceler;
	}

	@Override
	public String toString() {
		return "Il [id=" + id + ", ad=" + ad + ", ilceler=" + ilceler + "]";
	}

	
}
