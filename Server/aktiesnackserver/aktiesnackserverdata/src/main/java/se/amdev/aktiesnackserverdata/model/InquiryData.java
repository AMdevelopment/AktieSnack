package se.amdev.aktiesnackserverdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Inquiry")
public class InquiryData extends EntityModel {

	@Column(nullable = false)
	private String email;

	protected InquiryData(){
		
	}
	
	public InquiryData(String email){
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}
