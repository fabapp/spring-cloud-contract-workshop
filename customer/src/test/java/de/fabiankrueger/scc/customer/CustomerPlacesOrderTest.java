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
@AutoConfigureStubRunner(ids = "de.fabiankrueger.scc:cashier:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//@AutoConfigureStubRunner(ids = "de.fabiankrueger.scc:cashier:1.0-SNAPSHOT:stubs", stubsMode = StubRunnerProperties.StubsMode.REMOTE, repositoryRoot = "git://file:///Users/fkrueger/git/spring-cloud-contract-workshop/exercise/contracts-repo/")
public class CustomerPlacesOrderTest {


    @StubRunnerPort(value = "cashier")
    int cashierServerPort;

    @Test
    void customerPlacesOrder() throws JSONException {

        // Create order
        Order order = new Order();
        order.setQty(2);
        order.setMobileNumber("+49 160 5563487");
        order.setProduct("coffee");

        // send order request to Cashier endpoint (provided as scc stub)
        final ClientResponse response = WebClient.builder()
                .baseUrl("http://localhost:" + cashierServerPort + "/order")
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(order))
                .exchange()
                .block();

        // verify response
        assertThat(response.statusCode()).isSameAs(HttpStatus.OK);
        String expectedJsonResponse = "{\"id\": 1,\"product\": \"coffee\", \"qty\":2, \"amount\":2.86, \"mobileNumber\": \"+49 160 5563487\", \"timeOrdered\": \"2016-12-31T23:30:59Z\"}";
        final String json = response.bodyToMono(String.class).block();
        JSONAssert.assertEquals(expectedJsonResponse, json, true);
    }
}
