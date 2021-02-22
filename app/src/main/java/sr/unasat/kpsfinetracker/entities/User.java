package sr.unasat.kpsfinetracker.entities;

public class User {

    public int userId;
    public String username;
    public String password;
    public boolean isActive;

    public User(int userId , String username , String password, boolean isActive){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }
//    TO STRING METHODS


//    GETTERS AND SETTERS
    public int getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
