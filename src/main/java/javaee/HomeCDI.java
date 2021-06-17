package javaee;

import javaee.dto.StationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Named
@RequestScoped
public class HomeCDI implements Serializable {

    private static final Logger logger
            = LoggerFactory.getLogger(HomeCDI.class);

    @Inject
    @Push(channel = "station")
    private PushContext context;

    @EJB
    HomeEJB homeEJB;

    private StationDto stationDto;

    public StationDto getStationDto() {
        return stationDto;
    }

    public void setStationDto(StationDto stationDto) {
        this.stationDto = stationDto;
    }

    @PostConstruct
    public void init() {
        try {
            StationDto response = homeEJB.getRestData();
            logger.info("REST request made on Ejb-init. Response received " + response.toString());
            stationDto = response;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStand() {
        try {
            stationDto = homeEJB.getRestData();
            logger.info("Updating timetable. New data: "+ stationDto.toString());
            context.send("update");
            logger.info("Push message sent to websocket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testAjax(){
        System.out.println("AJAX!");
        try {
            stationDto = homeEJB.getRestData();
            logger.info("Ajax listener message. New rest request for station data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDate(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = "";
        dateString = format.format(date);
        return dateString;
    }
}