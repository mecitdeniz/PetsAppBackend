package com.mecitdeniz.petsAppRest.Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hayvanlar")
public class Hayvan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int sahipId;
	
	private String ad;
	
	private int turId;
	
	private int cinsId;
	
	private int cinsiyetId;
	
	private String resim1;
	private String resim2;
	private String resim3;
	private String resim4;
	
	@OneToOne
	@JoinColumn(name="cinsiyetId",insertable = false,updatable = false)
	private Cinsiyet cinsiyet;
	
	@OneToOne
	@JoinColumn(name="turId",insertable = false,updatable = false)
	private Tur tur;

	@OneToOne
	@JoinColumn(name="cinsId",insertable = false,updatable = false)
	private Cins cins;

	public Hayvan() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSahipId() {
		return sahipId;
	}

	public void setSahipId(int sahipId) {
		this.sahipId = sahipId;
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

	public int getCinsiyetId() {
		return cinsiyetId;
	}

	public void setCinsiyetId(int cinsiyetId) {
		this.cinsiyetId = cinsiyetId;
	}

	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getResim1() {
		return resim1;
	}

	public void setResim1(String resim1) {
		this.resim1 = resim1;
	}

	public String getResim2() {
		return resim2;
	}

	public void setResim2(String resim2) {
		this.resim2 = resim2;
	}

	public String getResim3() {
		return resim3;
	}

	public void setResim3(String resim3) {
		this.resim3 = resim3;
	}

	public String getResim4() {
		return resim4;
	}

	public void setResim4(String resim4) {
		this.resim4 = resim4;
	}
	
	

	public int getCinsId() {
		return cinsId;
	}

	public void setCinsId(int cinsId) {
		this.cinsId = cinsId;
	}

	public Tur getTur() {
		return tur;
	}

	public void setTur(Tur tur) {
		this.tur = tur;
	}

	public Cins getCins() {
		return cins;
	}

	public void setCins(Cins cins) {
		this.cins = cins;
	}

	@Override
	public String toString() {
		return "Hayvan [id=" + id + ", sahipId=" + sahipId + ", ad=" + ad + ", turId=" + turId + ", cinsId=" + cinsId
				+ ", cinsiyetId=" + cinsiyetId + ", resim1=" + resim1 + ", resim2=" + resim2 + ", resim3=" + resim3
				+ ", resim4=" + resim4 + ", cinsiyet=" + cinsiyet + ", tur=" + tur + ", cins=" + cins + "]";
	}



	

}
