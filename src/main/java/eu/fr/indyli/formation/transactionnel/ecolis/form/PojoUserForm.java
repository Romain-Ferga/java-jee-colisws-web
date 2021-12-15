package eu.fr.indyli.formation.transactionnel.ecolis.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PojoUserForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7142989958139265881L;
	private Integer idUtilisateur;
	private String civilite;
	private String name;
	private String login;
	private String password;
	private String email;
	private byte enabled;
	private Date derniereConnexion;
	private int anneeDeNaissance;
	private String telephone;
	private Date dateInscription;
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getCivilite() {
		return civilite;
	}
	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte getEnabled() {
		return enabled;
	}
	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}
	public Date getDerniereConnexion() {
		return derniereConnexion;
	}
	public void setDerniereConnexion(Date derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}
	public int getAnneeDeNaissance() {
		return anneeDeNaissance;
	}
	public void setAnneeDeNaissance(int anneeDeNaissance) {
		this.anneeDeNaissance = anneeDeNaissance;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}
	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
	
}
