package in.mkp.advertisers.v1.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "advertisers")
public class Advertiser {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "contact_name")
    private String contactName;

    @Column(nullable = false, name = "credit_limit")
    private Long creditLimit;

    public Advertiser() {
        super();
    }

    @Override
    public String toString() {
        return String.format("%d\t%s\t%s\t%s\t%d", id, name, contactName, creditLimit);
    }
}
