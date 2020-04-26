package com.example.demo;

import entity.Product;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import repository.ProductRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductRestServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductRepository mockRepository;

    /*
        {
            "timestamp": "2020-04-25T02:26:52.271+0000",
             "status": 400,
             "errors": [
                  "Please provide a category"
            ]
        }
     */
    @Test
    public void save_BadCategory_400() throws JSONException {

        String productJson = "{\"brand\":\"apple\",\"productName\":\"iPad\",\"price\":22.1,\"currencyCode\":\"USD\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(productJson, headers);

        // send json with POST
        ResponseEntity<String> response = restTemplate.postForEntity("/createProduct", entity, String.class);
        //printJSON(response);

        String expectedJson = "{\"status\":400,\"errors\":[\"Please provide a category\"]}";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

        verify(mockRepository, times(0)).save(any(Product.class));

    }

    /*
        {
            "timestamp": "2020-04-25T02:26:52.271+0000",
             "status": 400,
             "errors": [
                  "Please provide a price"
            ]
        }
     */
    @Test
    public void invalidPriceService_400() throws JSONException {

        String productJson = "{\"category\":\"electronics\",\"brand\":\"apple\",\"productName\":\"iPad\",\"currencyCode\":\"USD\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(productJson, headers);

        //Try exchange
        ResponseEntity<String> response = restTemplate.exchange("/createProduct", HttpMethod.POST, entity, String.class);

        String expectedJson = "{\"status\":400,\"errors\":[\"Please provide a price\"]}";
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JSONAssert.assertEquals(expectedJson, response.getBody(), false);

    }


    @Test
    public void testPostCall() throws JSONException {

        String productJson = "{\"category\":\"electronics\",\"brand\":\"apple\",\"productName\":\"iPad\",\"price\":322.1,\"currencyCode\":\"USD\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(productJson, headers);

        //Try exchange
        ResponseEntity<String> response = restTemplate.exchange("/createProduct", HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void testGetCall() throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //Try exchange
        ResponseEntity<String> response = restTemplate.exchange("/products", HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }



}
