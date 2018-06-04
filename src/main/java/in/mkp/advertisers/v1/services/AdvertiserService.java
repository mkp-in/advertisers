package in.mkp.advertisers.v1.services;


import in.mkp.advertisers.v1.entities.Advertiser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface AdvertiserService {

    @Select("SELECT id, name, contact_name, credit_limit FROM advertisers WHERE id=#{id}")
    Advertiser findAdvertiserById(final Integer id);

    @Insert("INSERT INTO advertisers (name, contact_name, credit_limit) VALUES (#{name}, #{contactName}, #{creditLimit})")
    void insertAdvertiser(final Advertiser advertiser);

    @Delete("DELETE FROM advertisers WHERE id=#{id}")
    void deleteAdvertiser(final Integer id);

    @Update("UPDATE advertisers SET name=#{name}, contact_name=#{contactName}, credit_limit=#{creditLimit} WHERE id=#{id}")
    void updateAdvertiser(final Advertiser advertiser);
}
