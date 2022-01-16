package br.com.nexfar.applicationtest.repository;

import br.com.nexfar.applicationtest.model.ListingAndReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ListingAndReviewRepository extends MongoRepository<ListingAndReview, Long> {

    @Query("{$or: [{'address.country' : { '$regex' : '?0' , $options: 'i'}}," +
            "{'description': { '$regex' : '?0' , $options: 'i'}}," +
            "{'name':{ '$regex' : '?0' , $options: 'i'}}]," +
            "$and: [{'number_of_reviews':{$gte:?1}}," +
            "{ 'price' : { $gte : ?2 } }," +
            "{ 'price' : { $lte : ?3 } }," +
            " { 'bedrooms' : { $gte : ?4 }}]}")
    List<ListingAndReview> newQueryAirbnb(String country, Long reviews,
                                          Double min, Double max, Integer bedrooms);


    @Query("{$or: [{'address.country' : { '$regex' : '?0' , $options: 'i'}}," +
            "{'description': { '$regex' : '?0' , $options: 'i'}}," +
            "{'name':{ '$regex' : '?0' , $options: 'i'}}]," +
            "$and: [{'number_of_reviews':{$gte:?1}}," +
            "{ 'price' : { $gte : ?2 } }," +
            " { 'bedrooms' : { $gte : ?3 }}]}")
    List<ListingAndReview> newQueryAirbnbWithoughtMax(String country, Long reviews,
                                                      Double min, Integer bedrooms);

}
