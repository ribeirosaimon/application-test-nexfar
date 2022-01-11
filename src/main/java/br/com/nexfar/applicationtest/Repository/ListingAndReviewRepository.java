package br.com.nexfar.applicationtest.Repository;

import br.com.nexfar.applicationtest.Model.ListingAndReview;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ListingAndReviewRepository extends MongoRepository<ListingAndReview, Long> {

}
