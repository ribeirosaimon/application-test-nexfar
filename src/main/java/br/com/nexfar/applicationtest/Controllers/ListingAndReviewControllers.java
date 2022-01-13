package br.com.nexfar.applicationtest.Controllers;

import br.com.nexfar.applicationtest.Dto.SearchTermDTO;
import br.com.nexfar.applicationtest.Model.ListingAndReview;
import br.com.nexfar.applicationtest.Repository.ListingAndReviewRepository;
import br.com.nexfar.applicationtest.Services.ListAndReviewService;
import br.com.nexfar.applicationtest.Services.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ListingAndReviewControllers {

    @Autowired
    ListAndReviewService service;

    @Autowired
    MongoService mongoService;

    @Autowired
    ListingAndReviewRepository repository;

    @GetMapping
    public String teste() {
        List<ListingAndReview> portugal = repository.findCompletQuery("portugal",100D,200D,2L,2);
        portugal.forEach(System.out::println);
        return "ok";
    }


    @PostMapping("/search")
    public ResponseEntity<?> searchListing(@RequestBody SearchTermDTO dto) {
        return ResponseEntity.ok(service.searchAirbnb(dto));
    }

}
