package br.com.nexfar.applicationtest.Controllers;

import br.com.nexfar.applicationtest.Dto.SearchTermDTO;
import br.com.nexfar.applicationtest.Repository.ListingAndReviewRepository;
import br.com.nexfar.applicationtest.Services.ListAndReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ListingAndReviewControllers {

    @Autowired
    ListAndReviewService service;

    @Autowired
    ListingAndReviewRepository repository;

    @GetMapping
    public ResponseEntity<?> teste(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchListing(@RequestBody SearchTermDTO dto) {
        System.out.println("AQUI ENTROU");
        return ResponseEntity.ok(service.searchAirbnb(dto));
    }

}
