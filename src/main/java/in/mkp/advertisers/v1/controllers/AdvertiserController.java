package in.mkp.advertisers.v1.controllers;

import in.mkp.advertisers.v1.entities.Advertiser;
import in.mkp.advertisers.v1.entities.AdvertiserCreditLimit;
import in.mkp.advertisers.v1.services.AdvertiserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {

    @Autowired
    private AdvertiserService advertiserService;

    @GetMapping("/get")
    public Advertiser getAdvertiser(@RequestParam(value = "id", required = true) final Integer id) {
        return this.advertiserService.findAdvertiserById(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertAdvertiser(@RequestBody final Advertiser advertiser) {
        this.advertiserService.insertAdvertiser(advertiser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete")
    public void deleteAdvertiser(@RequestParam(value = "id", required = true) final Integer id) {
        this.advertiserService.deleteAdvertiser(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdvertiser(@RequestParam(value = "id", required = true) final Integer id, @RequestBody final Advertiser advertiser) {
        final Advertiser result = this.advertiserService.findAdvertiserById(id);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        advertiser.setId(id);
        this.advertiserService.updateAdvertiser(advertiser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checkLimit")
    public AdvertiserCreditLimit checkLimit(@RequestParam(value = "id", required = true) final Integer id,
                                            @RequestParam(value = "requested", required = true) final Long requestedTransaction) {
        final Advertiser result = this.advertiserService.findAdvertiserById(id);
        final AdvertiserCreditLimit advertiserCreditLimit = new AdvertiserCreditLimit();
        advertiserCreditLimit.setRequestedTransaction(requestedTransaction);
        if (result == null) {
            advertiserCreditLimit.setAdvertiserNotFound(true);
        }
        else {
            advertiserCreditLimit.setAdvertiser(result);
            if (result.getCreditLimit() >= requestedTransaction) {
                advertiserCreditLimit.setEnoughCredit(true);
            }
        }
        return advertiserCreditLimit;

    }
}
