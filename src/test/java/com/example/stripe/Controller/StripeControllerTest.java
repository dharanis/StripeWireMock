package com.example.stripe.Controller;


import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
        /*(classes = StripeController.class,
        webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)*/
//@AutoConfigureWireMock(port = 8080)
public class StripeControllerTest {

//WireMock.configureFor("localhost", wireMockServer.port())
@Rule
public WireMockRule wireMockRule = new WireMockRule(8089);

    @Autowired
    StripeController stripeController;
    @Test
    public void exactUrlOnly() {
        wireMockRule.stubFor(get(urlEqualTo("/create"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    public void headerMatching() {
        stubFor(post(urlEqualTo("/create"))
                .withHeader("Accept", matching("text/.*"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    public void bodyMatching() {
        stubFor(post(urlEqualTo("/create"))
                .withRequestBody(matching("<status>OK</status>"))
                .withRequestBody(notMatching("<status>ERROR</status>"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    public void binaryBodyMatchingByteArray() {
        stubFor(post(urlEqualTo("/create"))
                .withRequestBody(binaryEqualTo(new byte[] { 1, 2, 3 }))
                .willReturn(ok()));
    }

    @Test
    public void multipartBodyMatching() {
        stubFor(post(urlEqualTo("/create"))
                .withMultipartRequestBody(aMultipart()
                        .withBody(binaryEqualTo("Content")))
                .willReturn(ok()));
    }

}
