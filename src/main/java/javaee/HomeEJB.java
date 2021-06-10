package javaee;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class HomeEJB {

    @PersistenceContext(unitName = "Unit1")
    private EntityManager entityManager;

    public List<UserEntity> getUsers() {
        List<UserEntity> list = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT user FROM users user");
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String> getRestData() throws IOException {
        URL url = new URL("http://localhost:8081/api/test");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine+"\n");
        }
        System.out.println(content);
        in.close();
        con.disconnect();
        return new Gson().fromJson(content.toString(), new TypeToken<List<String>>(){}.getType());
    }

}
