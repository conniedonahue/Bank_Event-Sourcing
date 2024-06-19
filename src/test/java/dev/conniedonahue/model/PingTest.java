package dev.conniedonahue.model;

import org.junit.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.*;

public class PingTest {


   /*
   PingResponse.serverTime is a String, so this test converts to LocalDateTime to confirm it's polling the server
    */
    @Test
    public void testPingConstructorAndGetters() {
        String serverTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateTime = LocalDateTime.parse(serverTime, DateTimeFormatter.ISO_DATE_TIME);


        Ping pingResponse = new Ping();
        LocalDateTime pingResponseLocalDateTime = LocalDateTime.parse(pingResponse.getServerTime(), DateTimeFormatter.ISO_DATE_TIME);

        // Confirms that two LocalDateTimes are equal down to the minute (ignoring seconds)
        assertThat(localDateTime).isEqualToIgnoringHours(pingResponseLocalDateTime);
        assertThat(localDateTime).isEqualToIgnoringMinutes(pingResponseLocalDateTime);
        assertThat(localDateTime).isEqualToIgnoringSeconds(pingResponseLocalDateTime);

        assertThat(pingResponse.getServerTime()).isInstanceOfAny(String.class);


    }

}
