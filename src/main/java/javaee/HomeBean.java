package javaee;

import javax.inject.Named;

@Named
public class HomeBean {
    private String text = "Home Bean text";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
