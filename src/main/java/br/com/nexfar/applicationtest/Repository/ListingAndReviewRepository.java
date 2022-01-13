package br.com.nexfar.applicationtest.Repository;

import br.com.nexfar.applicationtest.Model.ListingAndReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ListingAndReviewRepository extends MongoRepository<ListingAndReview, Long> {
    @Query("{ 'Address_Country' : { '$regex' : ?0 , $options: 'i'}}")
    List<ListingAndReview> findListingAndReviewByAddress_Country(String name);

}
