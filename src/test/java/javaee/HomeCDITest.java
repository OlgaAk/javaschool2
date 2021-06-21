package javaee;

import javaee.dto.TimetableItemDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class HomeCDITest {

    HomeCDI homeCDI = new HomeCDI();

    @Test
    public void getDate() {
       String actual = homeCDI.getDate();
       String expected = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
       Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAssignPlatforms() {
        List<Integer> platforms  = Arrays.asList(1,2,3,4,5);
        TimetableItemDto itemDto1 = new TimetableItemDto();
        TimetableItemDto itemDto2 = new TimetableItemDto();
        itemDto1.setDepartureTimeAsDate(new Date());
        itemDto2.setDepartureTimeAsDate(new Date());
        TimetableItemDto itemDto3 = new TimetableItemDto();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        itemDto3.setDepartureTimeAsDate(cal.getTime());
        List<TimetableItemDto> list = new ArrayList<>();
        TimetableItemDto itemDto4 = new TimetableItemDto();
        itemDto4.setDepartureTimeAsDate(cal.getTime());
        list.add(itemDto1);
        list.add(itemDto2);
        list.add(itemDto3);
        list.add(itemDto4);
        homeCDI.assignPlatforms(list, platforms);
        Assert.assertTrue(list.get(0).getPlatform()!=list.get(1).getPlatform());
        Assert.assertTrue(list.get(0).getPlatform()==list.get(2).getPlatform());
        Assert.assertTrue(list.get(2).getPlatform()!=list.get(3).getPlatform());
    }
}