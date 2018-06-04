package in.mkp.advertisers.v1.repositories;

import in.mkp.advertisers.v1.entities.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertiserRepository extends JpaRepository<Advertiser, Integer> {

    Advertiser findAdvertiserByName(String advertiserName);
}
