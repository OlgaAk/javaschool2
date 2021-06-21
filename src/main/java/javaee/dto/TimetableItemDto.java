package javaee.dto;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimetableItemDto implements Comparable<TimetableItemDto> {

    private long id;

    private long stationId;

    private String stationName;

    private String departureTime;

    private String arrivalTime;

    private Date departureTimeAsDate;

    private Date arrivalTimeAsDate;

    private String startTripStationName;

    private String endTripStationName;

    private String formattedArrivalTime;

    private String formattedDepartureTime;

    private int order;

    private int platform;

    public TimetableItemDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    } // get data from request

    public Date getDepartureTimeAsDate() {
        return departureTimeAsDate;
    }

    public void setDepartureTimeAsDate(Date departureTimeAsDate) {
        this.departureTimeAsDate = departureTimeAsDate;
    }

    public void setArrivalTimeAsDate(Date arrivalTimeAsDate) {
        this.arrivalTimeAsDate = arrivalTimeAsDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) { // get data from request
        this.arrivalTime = arrivalTime;
    }

    public String getStartTripStationName() {
        return startTripStationName;
    }

    public void setStartTripStationName(String startTripStationName) {
        this.startTripStationName = startTripStationName;
    }

    public String getEndTripStationName() {
        return endTripStationName;
    }

    public void setEndTripStationName(String endTripStationName) {
        this.endTripStationName = endTripStationName;
    }

    public long getStationId() {
        return stationId;
    }

    public void setStationId(long stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getArrivalTimeAsDate() {
        return arrivalTimeAsDate;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getFormattedArrivalTime() {
        return formattedArrivalTime;
    }

    public void setFormattedArrivalTime(String formattedArrivalTime) {
        this.formattedArrivalTime = formattedArrivalTime;
    }

    public String getFormattedDepartureTime() {
        return formattedDepartureTime;
    }

    public void setFormattedDepartureTime(String formattedDepartureTime) {
        this.formattedDepartureTime = formattedDepartureTime;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    @Override
    public int compareTo(TimetableItemDto t) {
        int result = 0;
        try {
            Date date1 = new SimpleDateFormat("HH:mm").parse(formattedDepartureTime);
            Date date2 = new SimpleDateFormat("HH:mm").parse(t.formattedDepartureTime);
            result = (int) (date1.getTime() - date2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        return "TimetableItemDto{" +
                "\nid=" + id +
                "\n, stationId=" + stationId +
                "\n, stationName='" + stationName + '\'' +
                "\n, departureTime='" + departureTime + '\'' +
                "\n, arrivalTime='" + arrivalTime + '\'' +
                "\n, departureTimeAsDate=" + departureTimeAsDate +
                "\n, arrivalTimeAsDate=" + arrivalTimeAsDate +
                "\n, startTripStationName='" + startTripStationName + '\'' +
                "\n, endTripStationName='" + endTripStationName + '\'' +
                "\n, formattedArrivalTime='" + formattedArrivalTime + '\'' +
                "\n, formattedDepartureTime='" + formattedDepartureTime + '\'' +
                ", order=" + order +
                '}';
    }




}

