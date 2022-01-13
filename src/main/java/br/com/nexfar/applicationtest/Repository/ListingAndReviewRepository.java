package br.com.nexfar.applicationtest.Repository;

import br.com.nexfar.applicationtest.Model.ListingAndReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ListingAndReviewRepository extends MongoRepository<ListingAndReview, Long> {

    @Query("{$or: [{'address.country' : { '$regex' : '?0' , $options: 'i'}}," +
            "{'description': { '$regex' : '?0' , $options: 'i'}}," +
            "{'name':{ '$regex' : '?0' , $options: 'i'}}]," +
            "$and: [{ 'price' : { $gte : ?1 } }," +
            " { 'price' : { $lte : ?2 } }," +
            " {'number_of_reviews':{$gte:?3}}," +
            " { 'bedrooms' : { $gte : ?4 }}]}")
    List<ListingAndReview> findCompletQuery(String country,
                                                                 Double max, Double min,
                                                                 Long reviews,
                                                                 Integer bedrooms);


}
