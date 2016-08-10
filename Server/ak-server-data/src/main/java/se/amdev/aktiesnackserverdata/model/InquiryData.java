package se.amdev.aktiesnackserverdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Inquiry")
public class InquiryData extends EntityModel {

	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private int active;

	protected InquiryData(){
		
	}
	
	public InquiryData(String email){
		this.email = email;
		this.active = 1;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getActive() {
		return active;
	}
	
	public InquiryData setActive(int active) {
		this.active = active;
		return this;
	}
}
