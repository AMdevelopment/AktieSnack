package se.amdev.ak_app.data.model;

public class UserWeb {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String creationTime;

    private String lastUpdatedTime;

    protected UserWeb() {
    }

    public UserWeb(String username, String email, String firstName, String lastName, String creationTime, String lastUpdatedTime) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationTime = creationTime;
        this.lastUpdatedTime = lastUpdatedTime;
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
        return creationTime;
    }

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }
}

