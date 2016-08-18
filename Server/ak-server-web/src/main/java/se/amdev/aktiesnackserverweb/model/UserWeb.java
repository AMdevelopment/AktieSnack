package se.amdev.aktiesnackserverweb.model;

import java.util.Date;

import se.amdev.aktiesnackserverdata.model.UserData;

public class UserWeb {

    private String username;
    
    private String password;

    private String firstName;

    private String lastName;

    private String email;
    
    private Date creationTime;

	private Date lastUpdatedTime;

    protected UserWeb() {
    }

    public UserWeb(String username, String email, String firstName, String lastName, String password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserWeb(UserData userData){
        this.username = userData.getUsername();
        this.email = userData.getEmail();
        this.firstName = userData.getFirstName();
        this.lastName = userData.getLastName();
        this.creationTime = userData.getCreationTime();
        this.lastUpdatedTime = userData.getLastUpdatedTime();
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
    
    public String getPassword() {
		return password;
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

        if (object instanceof UserWeb) {
            UserWeb otherObject = (UserWeb) object;

            return this.username.equals(otherObject.username) &&
                    this.email.equals(otherObject.email);
        }
        return false;
    }
    
    public String getCreationTime() {
		return creationTime.toString();
	}
	
	public String getLastUpdatedTime() {
		return lastUpdatedTime.toString();
	}
}

