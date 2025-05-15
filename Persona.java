import java.io.Serializable;

public class Persona implements Serializable {
    private String email;
    private char [] password1;
    private char [] password2;
    private String username;


    public Persona(String email, char[] password1, char[] password2, String username) {
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword1() {
        return password1;
    }

    public void setPassword1(char[] password1) {
        this.password1 = password1;
    }

    public char[] getPassword2() {
        return password2;
    }

    public void setPassword2(char[] password2) {
        this.password2 = password2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
