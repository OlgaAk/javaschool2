package javaee;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.util.List;

@Named
@SessionScoped
public class HomeCDI {
    private String text = "Home Bean text";
    private String login;
    private String password;
    private boolean logged;

    @EJB
    private HomeEJB homeEJB;

    public List<UserEntity> getAllUsers(){
        return homeEJB.getUsers();
    }

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
