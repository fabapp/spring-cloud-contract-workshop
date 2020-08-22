package de.fabiankrueger.scc.customer;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureStubRunner(
        ids = "de.fabiankrueger.scc:cashier:+:stubs",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CustomerPaysOrderTest {

    @StubRunnerPort("cashier")
    private int stubPort;

    @Test
    public void customerPaysOrder() throws JSONException {
        // create payment
        String payment = "{\"amountGiven\": 3}";

        // send post request to stub
        final ClientResponse response = WebClient.builder()
                .baseUrl("http://localhost:" + stubPort + "/order/1/payment")
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(payment))
                .exchange()
                .block();

        // verify response
        assertThat(response.statusCode()).isSameAs(HttpStatus.OK);
        String bodyJson = response.bodyToMono(String.class).block();

        String expectedResponse =   "{" +
                                        "\"amountAsked\": 2.86," +
                                        "\"amountGiven\": 3," +
                                        "\"changeReturned\": 0.14}" +
                                    "}";
        JSONAssert.assertEquals(expectedResponse, bodyJson, true);
    }
}
