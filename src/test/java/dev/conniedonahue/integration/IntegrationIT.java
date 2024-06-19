package dev.conniedonahue.integration;

import dev.conniedonahue.controller.TransactionController;
import dev.conniedonahue.model.Ping;
import dev.conniedonahue.service.*;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class IntegrationIT {



    private static Javalin app;
    private final JavalinJackson javalinJackson = new JavalinJackson();
//    private final String usersJson = javalinJackson.toJsonString(UserController.users);

    @BeforeClass
    public static void setup() {
        app = Javalin.create();
        BankLedger bankLedgerTest = new BankLedger();
        AccountManager accountManagerTest = new AccountManager();
        TransactionService transactionServiceTest = new TransactionServiceImpl(bankLedgerTest, accountManagerTest);
        TransactionController transactionControllerTest = new TransactionController(transactionServiceTest);

        app.get("/api/ping", transactionControllerTest.ping());

    }

    @AfterClass
    public static void tearDown() {
        app.stop();
    }

    @Test
    public void testGetPingEndpoint() {
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/api/ping").code()).isEqualTo(200);
            String serverTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
            LocalDateTime localDateTime = LocalDateTime.parse(serverTime, DateTimeFormatter.ISO_DATE_TIME);

            Response response = client.get("/api/ping");
            String responseBody = response.body().string();
            Ping ping = javalinJackson.fromJsonString(responseBody, Ping.class);

            assertThat(LocalDateTime.parse(ping.getServerTime(), DateTimeFormatter.ISO_DATE_TIME)).isEqualToIgnoringSeconds(localDateTime);
        });
    }
}

