package eu.fr.indyli.formation.transactionnel.ecolis.form;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

public class PojoModelAlerteForm {
	private Integer idAlerte;
	@NotEmpty
    private String villeDepart;
	@NotEmpty
    private String villeArrivee;
	/*
	@NotEmpty
	 @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateCreation;
	*/
    public Integer getIdAlerte() {
		return idAlerte;
	}
    public void setIdAlerte(Integer id) {
		this.idAlerte = id;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public String getVilleArrivee() {
		return villeArrivee;
	}
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}
	/*
	 * public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	*/
	public String toString(){
		return ToStringBuilder.reflectionToString(this); 
	}
}
