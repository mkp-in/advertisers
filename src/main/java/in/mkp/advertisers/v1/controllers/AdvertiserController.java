package in.mkp.advertisers.v1.controllers;

import in.mkp.advertisers.v1.entities.Advertiser;
import in.mkp.advertisers.v1.repositories.AdvertiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertiser")
public class AdvertiserController {

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @GetMapping("/getOne")
    public Advertiser getAdvertiser(@RequestParam(value = "name", required = true) final String name) {
        return this.advertiserRepository.findAdvertiserByName(name);
    }
}
