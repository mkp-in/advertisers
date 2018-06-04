package in.mkp.advertisers.v1.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdvertiserCreditLimit {

    private Advertiser advertiser;

    private Long requestedTransaction;

    private boolean enoughCredit;

    private boolean advertiserNotFound;

    public AdvertiserCreditLimit() {
    }
}
