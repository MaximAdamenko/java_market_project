package RoniAndMax;

abstract public class User {

    private String username;
    private String passWord;


    public User(String userName, String passWord) {
        setUserName(userName);
        setPassWord(passWord);
    }

    public User(User other) {
        setUserName(other.getUsername());
        setPassWord(other.getPassWord());
    }


    public String getPassWord() {
        return passWord;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return false;
        }
        this.username = userName;
        return true;
    }

    public boolean setPassWord(String passWord) {
        this.passWord = passWord;
        return true;
    }

    @Override
    public String toString() {
        return "username: " + username +
                "\n    password: " + passWord;
    }
}
