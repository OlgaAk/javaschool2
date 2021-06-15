package javaee;

import javaee.dto.StationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
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
@SessionScoped
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
            logger.info(response.toString());
            stationDto = response;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStand() {
        try {
            stationDto = homeEJB.getRestData();
            logger.info(stationDto.toString());
            context.send("update");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testAjax(){
        System.out.println("AJAX!");
        try {
            stationDto = homeEJB.getRestData();
            logger.info(stationDto.toString());
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