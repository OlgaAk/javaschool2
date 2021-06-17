package javaee;

import com.google.gson.Gson;
import javaee.dto.StationDto;
import javaee.jms.JmsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Stateless
public class HomeEJB {

    @PersistenceContext(unitName = "Unit1") //not used in this app
    private EntityManager entityManager;

    private static final Logger logger
            = LoggerFactory.getLogger(HomeEJB.class);

    public StationDto getRestData() throws IOException {
        URL url = new URL("http://localhost:8081/api/station/1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine+"\n");
        }
        in.close();
        con.disconnect();
        StationDto stationDto = new StationDto();
        try{
            stationDto = new Gson().fromJson(content.toString(), StationDto.class);
        } catch (Exception ex){
            logger.error(ex.getCause().getMessage());
        }
        return stationDto;
    }

}
