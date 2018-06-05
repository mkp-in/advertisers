package in.mkp.advertisers.v1.controllers;

import in.mkp.advertisers.v1.entities.Advertiser;
import in.mkp.advertisers.v1.entities.AdvertiserCreditLimit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdvertiserControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testGetForAdvertiserPresent() {
        final ResponseEntity<Advertiser> entity = template.getForEntity("/api/advertiser/get?id=1", Advertiser.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        final Advertiser response = entity.getBody();
        assertEquals(1, (int)response.getId());
        assertEquals("AdvertiserA", response.getName());
        assertEquals("Advertiser A Contact", response.getContactName());
        assertEquals(5000L, (long)response.getCreditLimit());
    }

    @Test
    public void testGetForAdvertiserNotPresent() {
        final ResponseEntity<Advertiser> entity = template.getForEntity("/api/advertiser/get?id=666", Advertiser.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNull(entity.getHeaders().getContentType());
        assertNull(entity.getBody());
    }

    @Test
    public void testPostAdvertiser() {
        final Advertiser advertiser = new Advertiser();
        advertiser.setName("AdvTest");
        advertiser.setContactName("AdvTest");
        advertiser.setCreditLimit(999L);

        final ResponseEntity<String> response = this.template.postForEntity("/api/advertiser/insert", advertiser, String.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        final ResponseEntity<Advertiser> entity = template.getForEntity("/api/advertiser/get?id=5", Advertiser.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        final Advertiser responseBack = entity.getBody();
        assertEquals(5, (int)responseBack.getId());
        assertEquals("AdvTest", responseBack.getName());
        assertEquals("AdvTest", responseBack.getContactName());
        assertEquals(999L, (long)responseBack.getCreditLimit());
    }

    @Test
    public void testDeleteAdvertiser() {
        this.template.delete("/api/advertiser/delete?id=3");
        final ResponseEntity<Advertiser> entity = template.getForEntity("/api/advertiser/get?id=3", Advertiser.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertNull(entity.getHeaders().getContentType());
        assertNull(entity.getBody());
    }

    @Test
    public void testCheckingAvailableCreditLimit() {
        final ResponseEntity<AdvertiserCreditLimit> entity = template.getForEntity("/api/advertiser/checkLimit?id=1&requested=4000", AdvertiserCreditLimit.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        final AdvertiserCreditLimit response = entity.getBody();
        assertEquals(1, (int)response.getAdvertiser().getId());
        assertEquals("AdvertiserA", response.getAdvertiser().getName());
        assertEquals("Advertiser A Contact", response.getAdvertiser().getContactName());
        assertEquals(5000L, (long)response.getAdvertiser().getCreditLimit());
        assertTrue(response.isEnoughCredit());
        assertFalse(response.isAdvertiserNotFound());
    }

    @Test
    public void testCheckingBelowCreditLimit() {
        final ResponseEntity<AdvertiserCreditLimit> entity = template.getForEntity("/api/advertiser/checkLimit?id=1&requested=9000", AdvertiserCreditLimit.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        final AdvertiserCreditLimit response = entity.getBody();
        assertEquals(1, (int)response.getAdvertiser().getId());
        assertEquals("AdvertiserA", response.getAdvertiser().getName());
        assertEquals("Advertiser A Contact", response.getAdvertiser().getContactName());
        assertEquals(5000L, (long)response.getAdvertiser().getCreditLimit());
        assertFalse(response.isEnoughCredit());
        assertFalse(response.isAdvertiserNotFound());
    }

    @Test
    public void testCheckingAvailableCreditLimitAdvertiserNotFound() {
        final ResponseEntity<AdvertiserCreditLimit> entity = template.getForEntity("/api/advertiser/checkLimit?id=999&requested=4000", AdvertiserCreditLimit.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        final AdvertiserCreditLimit response = entity.getBody();
        assertNull(response.getAdvertiser());
        assertFalse(response.isEnoughCredit());
        assertTrue(response.isAdvertiserNotFound());
    }

    @Test
    public void testUpdateAdvertiserPresent() {
        final Advertiser advertiser = new Advertiser();
        advertiser.setName("AdvUpdate");
        advertiser.setContactName("AdvUpdate");
        advertiser.setCreditLimit(1010L);

        HttpEntity<Advertiser> requestEntity = new HttpEntity(advertiser);
        ResponseEntity<String> response = this.template.exchange("/api/advertiser/update?id=2", HttpMethod.PUT, requestEntity, String.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        final ResponseEntity<Advertiser> entity = template.getForEntity("/api/advertiser/get?id=2", Advertiser.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, entity.getHeaders().getContentType());
        final Advertiser responseBack = entity.getBody();
        assertEquals(2, (int)responseBack.getId());
        assertEquals("AdvUpdate", responseBack.getName());
        assertEquals("AdvUpdate", responseBack.getContactName());
        assertEquals(1010L, (long)responseBack.getCreditLimit());
    }

    @Test
    public void testUpdateAdvertiserNotPresent() {
        final Advertiser advertiser = new Advertiser();
        advertiser.setName("AdvUpdate");
        advertiser.setContactName("AdvUpdate");
        advertiser.setCreditLimit(1010L);

        HttpEntity<Advertiser> requestEntity = new HttpEntity(advertiser);
        ResponseEntity<String> response = this.template.exchange("/api/advertiser/update?id=999", HttpMethod.PUT, requestEntity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}