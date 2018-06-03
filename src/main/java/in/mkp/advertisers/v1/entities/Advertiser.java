package in.mkp.advertisers.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class Advertiser {

    @Id
    private Integer id;

    private String name;

    private String contactName;

    private Long creditLimit;
}
