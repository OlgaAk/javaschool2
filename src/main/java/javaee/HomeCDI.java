package javaee;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class HomeCDI implements Serializable {

    @Inject
    @Push(channel = "stats")
    private PushContext context;

    @EJB
    HomeEJB homeEJB;

    private List<String> stats;

    @PostConstruct
    public void init() {
        try {
            List<String> response = homeEJB.getRestData();
            System.out.println(response);
            stats = response;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStand() {
        List<String> response = null;
        try {
            response = homeEJB.getRestData();
            System.out.println(response);
            stats = response;
            context.send("update");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getStats() {
        return stats;
    }

    public void setStats(List<String> stats) {
        this.stats = stats;
    }

    public void testAjax(){
        System.out.println("AJAX!");
        try {
            List<String> response = homeEJB.getRestData();
            System.out.println(response);
            stats = response;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}