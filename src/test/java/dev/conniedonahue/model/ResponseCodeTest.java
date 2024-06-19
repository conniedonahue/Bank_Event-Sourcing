package dev.conniedonahue.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ResponseCodeTest {

    @Test
    public void testResponseCodeEnum() {

        assertEquals("APPROVED", ResponseCode.APPROVED.toString());
        assertEquals(ResponseCode.APPROVED, ResponseCode.valueOf("APPROVED"));

        assertEquals("DECLINED", ResponseCode.DECLINED.toString());
        assertEquals(ResponseCode.DECLINED, ResponseCode.valueOf("DECLINED"));
    }
}