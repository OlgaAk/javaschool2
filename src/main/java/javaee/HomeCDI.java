package javaee;

import javaee.dto.StationDto;
import javaee.dto.TimetableItemDto;
import javaee.util.DateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


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
        stationDto = getRestDataProcessed();
    }

    public void updateStand() {
        stationDto = getRestDataProcessed();
        logger.info("Updating timetable. New data: " + stationDto.toString());
        context.send("update");
        logger.info("Push message sent to websocket");
    }

    public void testAjax() {
        System.out.println("AJAX!");
        stationDto = getRestDataProcessed();
        logger.info("Ajax listener message. New rest request for station data");
    }

    public StationDto getRestDataProcessed() {
        try {
            stationDto = homeEJB.getRestData();
            logger.info("REST request made on Ejb-init. Response received " + stationDto.toString());
            stationDto.getTimetableItems().forEach(t -> {
                t.setDepartureTimeAsDate(null);
                t.setArrivalTimeAsDate(null);
                if (t.getFormattedArrivalTime() != "-") {
                    t.setArrivalTimeAsDate(DateTimeConverter.getTimeFromHoursString(t.getFormattedArrivalTime()));
                }
                if (t.getFormattedDepartureTime() != "-") {
                    t.setDepartureTimeAsDate(DateTimeConverter.getTimeFromHoursString(t.getFormattedDepartureTime()));
                }
            });
            assignPlatforms(stationDto.getTimetableItems(), stationDto.getPlatforms());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stationDto;
    }

    /**
     * Method assigns platform to train, one train at a time on one platform
     *
     * @param items     TimetableItems
     * @param platforms
     */
    public void assignPlatforms(List<TimetableItemDto> items, List<Integer> platforms) {
        Map<Integer, List<TimetableItemDto>> map = new HashMap<>();
        //initialize map with platform numbers and empty timetable lists
        for (int i : platforms) {
            map.put(i, new ArrayList<>());
        }
        AtomicInteger countAssignedTrains = new AtomicInteger();
        tLoop:
        for (TimetableItemDto t : items) {
            //iterate through platforms
            mapLoop:
            for (Map.Entry<Integer, List<TimetableItemDto>> entry : map.entrySet()) {
                if (countAssignedTrains.get() == items.size()) break tLoop;
                if (entry.getValue().size() == 0) {
                    map.get(entry.getKey()).add(t);
                    t.setPlatform(entry.getKey());
                    countAssignedTrains.getAndIncrement();
                    continue tLoop;
                }
                //iterate through trains assigned to platform

                for (TimetableItemDto assignedTI : entry.getValue()) {
                    // if next train is intersecting with previous ones, go to next platform
                    if (isTimeIntersecting(assignedTI.getDepartureTimeAsDate(), assignedTI.getArrivalTimeAsDate(),
                            t.getDepartureTimeAsDate(), t.getArrivalTimeAsDate())) {
                        continue mapLoop;
                    }
                }
                entry.getValue().add(t);
                t.setPlatform(entry.getKey());
                countAssignedTrains.getAndIncrement();
                continue tLoop;

            }
        }
    }


    private boolean isTimeIntersecting(Date departureTime1, Date arrivalTime1,
                                       Date departureTime2, Date arrivalTime2) {
        departureTime1 = handleNullTime(departureTime1, arrivalTime1);
        departureTime2 = handleNullTime(departureTime2, arrivalTime2);
        arrivalTime1 = handleNullTime(departureTime1, arrivalTime1);
        arrivalTime2 = handleNullTime(departureTime2, arrivalTime2);
        if (departureTime1.getTime() <= arrivalTime2.getTime()
                && departureTime1.getTime() >= departureTime2.getTime()
                || arrivalTime1.getTime() <= arrivalTime2.getTime()
                && arrivalTime1.getTime() >= departureTime2.getTime()) {
            return true;
        }
        return false;
    }

    private Date handleNullTime(Date time1, Date time2) {
        if (time1 == null) {
            if (time2 == null) throw new IllegalArgumentException("Timetable Items has no time");
            return time2;
        }
        return time1;
    }

    /**
     * Method for displaying today on frontend, format 18.05.2021
     *
     * @return date as String
     */
    public String getDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String dateString = "";
        dateString = format.format(date);
        return dateString;
    }
}