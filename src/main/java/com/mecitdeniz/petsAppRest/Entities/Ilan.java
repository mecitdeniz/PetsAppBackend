package com.mecitdeniz.petsAppRest.Entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ilanlar")
public class Ilan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	private int yayinlayanId;
	
	private String yayinTarihi;
	
	private Date onayTarihi;
	
	private int onaylayanId;
	
	private String baslik;
	
	private String aciklama;
	
	private int ilId;
	
	private int ilceId;
	
	private int durumId;
	
	@OneToOne
	@JoinColumn(name="ilId", insertable = false ,updatable = false)
	private Il il;
	
	@OneToOne
	@JoinColumn(name="ilceId", insertable = false ,updatable = false)
	private Ilce ilce;

	@OneToOne
	@JoinColumn(name="durumId",insertable = false,updatable = false)
	private Durum durum;
	
	
	private int  tipId;
	
	@OneToOne
	@JoinColumn(name="tipId",insertable = false,updatable = false)
	private Tip tip;
	
	
	private String baslangicTarihi;
	
	private String bitisTarihi;
	
	@ManyToMany
	@JoinTable(
        name="hayvanIlan",
        joinColumns=
            @JoinColumn(name="ilanId"),
        inverseJoinColumns=
            @JoinColumn(name="hayvanId")
    )
	private List<Hayvan> hayvanlar = new ArrayList<Hayvan>();
	
	@OneToOne
	@JoinColumn(name="yayinlayanId", insertable = false ,updatable = false)
	private Kullanici yayinlayan;
	

	public Ilan() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYayinlayanId() {
		return yayinlayanId;
	}
	public void setYayinlayanId(int yayinlayanId) {
		this.yayinlayanId = yayinlayanId;
	}
	
	public String getYayinTarihi() {
		return yayinTarihi;
	}
	public void setYayinTarihi(String yayinTarihi) {
		this.yayinTarihi = yayinTarihi;
	}
	
	public Date getOnayTarihi() {
		return onayTarihi;
	}
	public void setOnayTarihi(Date onayTarihi) {
		this.onayTarihi = onayTarihi;
	}
	public int getOnaylayanId() {
		return onaylayanId;
	}
	public void setOnaylayanId(int onaylayanId) {
		this.onaylayanId = onaylayanId;
	}
	
	public String getBaslik() {
		return baslik;
	}
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
	
	public Tip getTip() {
		return tip;
	}
	public void setTip(Tip tip) {
		this.tip = tip;
	}
	public Il getIl() {
		return il;
	}
	public void setIl(Il il) {
		this.il = il;
	}
	public Ilce getIlce() {
		return ilce;
	}
	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
	}
	public int getIlId() {
		return ilId;
	}
	public void setIlId(int ilId) {
		this.ilId = ilId;
	}
	public int getIlceId() {
		return ilceId;
	}
	public void setIlceId(int ilceId) {
		this.ilceId = ilceId;
	}
	public int getDurumId() {
		return durumId;
	}
	public void setDurumId(int durumId) {
		this.durumId = durumId;
	}
	public int getTipId() {
		return tipId;
	}
	public void setTipId(int tipId) {
		this.tipId = tipId;
	}
	public String getBaslangicTarihi() {
		return baslangicTarihi;
	}
	public void setBaslangicTarihi(String baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}
	public String getBitisTarihi() {
		return bitisTarihi;
	}
	public void setBitisTarihi(String bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}
	
	
	public Durum getDurum() {
		return durum;
	}
	public void setDurum(Durum durum) {
		this.durum = durum;
	}
	public List<Hayvan> getHayvanlar() {
		return hayvanlar;
	}
	public void setHayvanlar(List<Hayvan> hayvanlar) {
		this.hayvanlar = hayvanlar;
	}
	public Kullanici getYayinlayan() {
		return yayinlayan;
	}
	public void setYayinlayan(Kullanici yayinlayan) {
		this.yayinlayan = yayinlayan;
	}
	
	@Override
	public String toString() {
		return "Ilan [id=" + id + ", yayinlayanId=" + yayinlayanId + ", yayinTarihi=" + yayinTarihi + ", onayTarihi="
				+ onayTarihi + ", onaylayanId=" + onaylayanId + ", baslik=" + baslik + ", aciklama=" + aciklama
				+ ", ilId=" + ilId + ", ilceId=" + ilceId + ", durumId=" + durumId + ", il=" + il + ", ilce=" + ilce
				+ ", durum=" + durum + ", tipId=" + tipId + ", tip=" + tip + ", baslangicTarihi=" + baslangicTarihi
				+ ", bitisTarihi=" + bitisTarihi + ", hayvanlar=" + hayvanlar + ", yayinlayan=" + yayinlayan + "]";
	}
	
		
}
