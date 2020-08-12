package com.mecitdeniz.petsAppRest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "yorumlar")
public class Yorum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int yorumlayanId;
	
	private int yorumlananId;
	
	private String aciklama;
	
	private String yorumlamaTarihi;
	
	private Float oy;
	
	private int ilanId;


	@OneToOne
	@JoinColumn(name="yorumlayanId",insertable = false,updatable = false)
	private Kullanici yorumlayan;

	public Yorum() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYorumlayanId() {
		return yorumlayanId;
	}

	public void setYorumlayanId(int yorumlayanId) {
		this.yorumlayanId = yorumlayanId;
	}

	public int getYorumlananId() {
		return yorumlananId;
	}

	public void setYorumlananId(int yorumlananId) {
		this.yorumlananId = yorumlananId;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getYorumlamaTarihi() {
		return yorumlamaTarihi;
	}

	public void setYorumlamaTarihi(String yorumlamaTarihi) {
		this.yorumlamaTarihi = yorumlamaTarihi;
	}

	public Float getOy() {
		return oy;
	}

	public void setOy(Float oy) {
		this.oy = oy;
	}
	
	

	public int getIlanId() {
		return ilanId;
	}

	public void setIlanId(int ilanId) {
		this.ilanId = ilanId;
	}
	
	

	public Kullanici getYorumlayan() {
		return yorumlayan;
	}

	public void setYorumlayan(Kullanici yorumlayan) {
		this.yorumlayan = yorumlayan;
	}

	@Override
	public String toString() {
		return "Yorum [id=" + id + ", yorumlayanId=" + yorumlayanId + ", yorumlananId=" + yorumlananId + ", aciklama="
				+ aciklama + ", yorumlamaTarihi=" + yorumlamaTarihi + ", oy=" + oy + ", ilanId=" + ilanId
				+ ", yorumlayan=" + yorumlayan + "]";
	}

	

}
