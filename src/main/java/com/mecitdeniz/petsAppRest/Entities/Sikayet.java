package com.mecitdeniz.petsAppRest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sikayetler")
public class Sikayet {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String aciklama;

	
	private int sikayetEdenId;
	
	
	private int ilanId;

	
	@OneToOne
	@JoinColumn(name = "ilanId",insertable = false,updatable = false)
	private Ilan ilan;
	
	
	@OneToOne
	@JoinColumn(name = "sikayetEdenId",insertable = false,updatable = false)
	private Kullanici sikayetEden;
	
	public Sikayet() {
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public int getSikayetEdenId() {
		return sikayetEdenId;
	}

	public void setSikayetEdenId(int sikayetEdenId) {
		this.sikayetEdenId = sikayetEdenId;
	}

	public int getIlanId() {
		return ilanId;
	}

	public void setIlanId(int ilanId) {
		this.ilanId = ilanId;
	}

	public Ilan getIlan() {
		return ilan;
	}

	public void setIlan(Ilan ilan) {
		this.ilan = ilan;
	}

	
	public Kullanici getSikayetEden() {
		return sikayetEden;
	}


	public void setSikayetEden(Kullanici sikayetEden) {
		this.sikayetEden = sikayetEden;
	}


	@Override
	public String toString() {
		return "Sikayet [id=" + id + ", aciklama=" + aciklama + ", sikayetEdenId=" + sikayetEdenId + ", ilanId="
				+ ilanId + ", ilan=" + ilan + ", sikayetEden=" + sikayetEden + "]";
	}


	
	
	

}
