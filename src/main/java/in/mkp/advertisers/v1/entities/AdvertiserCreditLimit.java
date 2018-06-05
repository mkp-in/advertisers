package in.mkp.advertisers.v1.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AdvertiserCreditLimit {

    private Advertiser advertiser;

    private Long requestedTransaction;

    private boolean enoughCredit;

    private boolean advertiserNotFound;

    public AdvertiserCreditLimit() {
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%s\t%d\t%d\t%s\t%s",
                this.getAdvertiser().getId(),
                this.getAdvertiser().getName(),
                this.getAdvertiser().getContactName(),
                this.getAdvertiser().getCreditLimit(),
                this.getRequestedTransaction(),
                this.isEnoughCredit(),
                this.isAdvertiserNotFound());
    }
}
