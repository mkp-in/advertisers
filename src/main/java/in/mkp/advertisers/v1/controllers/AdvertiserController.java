package in.mkp.advertisers.v1.controllers;

import java.util.Optional;

import in.mkp.advertisers.v1.entities.Advertiser;
import in.mkp.advertisers.v1.repositories.AdvertiserRepository;
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
    private AdvertiserRepository advertiserRepository;

    @GetMapping("/get")
    public Optional<Advertiser> getAdvertiser(@RequestParam(value = "id", required = true) final Integer id) {
        return this.advertiserRepository.findById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAdvertiser(@RequestParam(value = "id", required = true) final Integer id) {
       this.advertiserRepository.deleteById(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<String> insertAdvertiser(@RequestBody final Advertiser advertiser) {
        final Advertiser result = this.advertiserRepository.save(advertiser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAdvertiser(@RequestParam(value = "id", required = true) final Integer id, @RequestBody final Advertiser advertiser) {
        Optional<Advertiser> result = this.advertiserRepository.findById(id);

        if (!result.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        advertiser.setId(id);
        this.advertiserRepository.save(advertiser);
        return ResponseEntity.noContent().build();
    }


}
