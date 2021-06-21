package javaee.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StationDto {

    private long id;

    private String name;

    private List<Integer> platforms = Arrays.asList(1,2,3,4,5);

    private List<TimetableItemDto> timetableItems;

    public StationDto() {
    }

    public StationDto(String id){
        this.id = Long.parseLong(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TimetableItemDto> getTimetableItems() {
        return timetableItems;
    }

    public void setTimetableItems(List<TimetableItemDto> timetableItems) {
        this.timetableItems = timetableItems;
    }

    public List<TimetableItemDto> getTimetableItemsDeparture(){
        return timetableItems
                .stream()
                .filter(item -> !item.getEndTripStationName().equals(item.getStationName()))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<TimetableItemDto> getTimetableItemsArrival(){
        return timetableItems.stream().filter(item -> {
            return !item.getStartTripStationName().equals(item.getStationName());
        }).sorted().collect(Collectors.toList());
    }

    public List<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Integer> platforms) {
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "StationDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timetableItems=" + timetableItems +
                '}';
    }
}
