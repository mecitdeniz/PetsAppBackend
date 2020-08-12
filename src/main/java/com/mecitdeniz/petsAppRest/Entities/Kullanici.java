package com.mecitdeniz.petsAppRest.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="kullanicilar")
public class Kullanici {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String ad;
	
	private String soyAd;
	
	private String email;
	
	private String profilResmi;
	
	private String sifre;
	
	private String kullaniciAdi;
	
	private String bio;
	
	private int cinsiyetId;
	
	
	@OneToOne
	@JoinColumn(name="cinsiyetId",insertable = false,updatable = false)
	private Cinsiyet cinsiyet;
	
	
	private int rolId;
	
	@OneToOne
	@JoinColumn(name="rolId",insertable = false,updatable = false)
	private Rol rol;
	
	public Kullanici() {
		
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
	public String getSoyAd() {
		return soyAd;
	}
	public void setSoyAd(String soyAd) {
		this.soyAd = soyAd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilResmi() {
		return profilResmi;
	}
	public void setProfilResmi(String profilResmi) {
		this.profilResmi = profilResmi;
	}
	
	public String getKullaniciAdi() {
		return kullaniciAdi;
	}
	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}
	
	public int getCinsiyetId() {
		return cinsiyetId;
	}
	public void setCinsiyetId(int cinsiyetId) {
		this.cinsiyetId = cinsiyetId;
	}

	public String getSifre() {
		return sifre;
	}
	public void setSifre(String sifre) {
		this.sifre = sifre;
	}
	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}
	
	public int getRolId() {
		return rolId;
	}
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@Override
	public String toString() {
		return "Kullanici [id=" + id + ", ad=" + ad + ", soyAd=" + soyAd + ", email=" + email + ", profilResmi="
				+ profilResmi + ", sifre=" + sifre + ", kullaniciAdi=" + kullaniciAdi + ", bio=" + bio + ", cinsiyetId="
				+ cinsiyetId + ", cinsiyet=" + cinsiyet + ", rolId=" + rolId + ", rol=" + rol + "]";
	}
	
	
	
}
