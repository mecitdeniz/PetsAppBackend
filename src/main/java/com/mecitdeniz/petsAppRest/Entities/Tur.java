package com.mecitdeniz.petsAppRest.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "turler")
public class Tur {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String ad;
	
	
	@OneToMany
	@JoinColumn(name="turId", insertable = false ,updatable = false)
	private List<Cins> cinsler;

	public Tur() {
		
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

	
	public List<Cins> getCinsler() {
		return cinsler;
	}

	public void setCinsler(List<Cins> cinsler) {
		this.cinsler = cinsler;
	}

	@Override
	public String toString() {
		return "Tur [id=" + id + ", ad=" + ad + ", cinsler=" + cinsler + "]";
	}
	
}
