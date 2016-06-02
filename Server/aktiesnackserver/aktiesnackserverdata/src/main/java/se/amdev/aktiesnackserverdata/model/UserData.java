package se.amdev.aktiesnackserverdata.model;

import javax.persistence.*;

@Entity(name = "Users")
public class UserData extends EntityModel {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String status;
	
	protected UserData() {
	}

	public UserData(String username, String email, String firstName, String lastName) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = "Active";
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getStatus() {
		return status;
	}

	public UserData setStatus(String status) {
		this.status = status;
		return this;
	}

	public UserData setEmail(String email) {
		this.email = email;
		return this;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result += 17 * username.hashCode();
		result += 17 * email.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}

		if (object instanceof UserData) {
			UserData otherObject = (UserData) object;

			return this.username.equals(otherObject.username) &&
					this.email.equals(otherObject.email);
		}
		return false;
	}
}
