package javaee;

import javax.inject.Named;

@Named
public class HomeBean {
    private String text = "Home Bean text";
    private String login;
    private String password;
    private boolean logged;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public void doLogin(){
        logged = true;
    }
}
