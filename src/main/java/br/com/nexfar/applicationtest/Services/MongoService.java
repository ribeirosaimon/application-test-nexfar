package br.com.nexfar.applicationtest.Services;

import org.springframework.stereotype.Service;

@Service
public class MongoService {

    public String createCompleteQuery(String country, String min, String max, String reviews, String bedrooms) {
        String adressCountry = String.format("{'address.country' : { '$regex' : '%s' , $options: 'i'}}", country);
        String description = String.format("{'description': { '$regex' : '%s' , $options: 'i'}}", country);
        String name = String.format("{'name':{ '$regex' : '%s' , $options: 'i'}}", country);
        String price = String.format("{ 'price' : { $gte : %s } }, { 'price' : { $lte : %s } }", min, max);
        String numberReviews = String.format("{'number_of_reviews':{$gte:%s}}", reviews);
        String bedroomsQuantity = String.format("{ 'bedrooms' : { $gte : %s }}", bedrooms);
        String orQueryString = String.format("{$or: [%s,%s,%s],", adressCountry, description, name);
        String andQueryString = String.format("$and: [%s, %s, %s]}", price, numberReviews, bedroomsQuantity);
        return orQueryString + andQueryString;
    }
}
