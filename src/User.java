import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class User {
    private Profile profile;
    private String username;
    private String password;

    public User() {
    }
    public User(Profile profile, String username, String password) {
        this.profile = profile;
        this.username = username;
        this.password = PasswordController.hashString(password);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordController.hashString(password);
    }

}
