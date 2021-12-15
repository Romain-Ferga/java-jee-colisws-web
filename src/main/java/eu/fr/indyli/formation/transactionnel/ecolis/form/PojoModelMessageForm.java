package eu.fr.indyli.formation.transactionnel.ecolis.form;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import eu.fr.indyli.formation.business.entity.Annonce;
import eu.fr.indyli.formation.business.entity.Utilisateur;

public class PojoModelMessageForm {
	
	private Integer idMessage;
	private Annonce annonce;
	private Utilisateur utilisateur;
	private String corps;
	private Date dateCreation;
	
	public Integer getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Integer idMessage) {
		this.idMessage = idMessage;
	}
	public Annonce getAnnonce() {
		return annonce;
	}
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public String getCorps() {
		return corps;
	}
	public void setCorps(String corps) {
		this.corps = corps;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
}
