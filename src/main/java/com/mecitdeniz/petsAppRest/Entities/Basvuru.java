package com.mecitdeniz.petsAppRest.Entities;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "basvurular")
public class Basvuru {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int basvuranId;
	private int ilanId;
	private String aciklama;
	
	private int durumId;
	private Date basvuruTarihi;
	
	@OneToOne
	@JoinColumn(name="basvuranId",insertable = false,updatable = false)
	private Kullanici basvuran;

	@OneToOne
	@JoinColumn(name="ilanId",insertable = false,updatable = false)
	private Ilan ilan;
	
	public Basvuru() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBasvuranId() {
		return basvuranId;
	}

	public void setBasvuranId(int basvuranId) {
		this.basvuranId = basvuranId;
	}

	public int getIlanId() {
		return ilanId;
	}

	public void setIlanId(int ilanId) {
		this.ilanId = ilanId;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public Kullanici getBasvuran() {
		return basvuran;
	}

	public void setBasvuran(Kullanici basvuran) {
		this.basvuran = basvuran;
	}
	
	public Ilan getIlan() {
		return ilan;
	}

	public void setIlan(Ilan ilan) {
		this.ilan = ilan;
	}

	
	
	public int getDurumId() {
		return durumId;
	}

	public void setDurumId(int durumId) {
		this.durumId = durumId;
	}
	
	

	public Date getBasvuruTarihi() {
		return basvuruTarihi;
	}

	public void setBasvuruTarihi(Date basvuruTarihi) {
		this.basvuruTarihi = basvuruTarihi;
	}

	@Override
	public String toString() {
		return "Basvuru [id=" + id + ", basvuranId=" + basvuranId + ", ilanId=" + ilanId + ", aciklama=" + aciklama
				+ ", durumId=" + durumId + ", basvuruTarihi=" + basvuruTarihi + ", basvuran=" + basvuran + ", ilan="
				+ ilan + "]";
	}

	
	
}
