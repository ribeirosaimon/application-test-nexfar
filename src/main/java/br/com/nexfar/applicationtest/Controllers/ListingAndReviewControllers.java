package br.com.nexfar.applicationtest.Controllers;

import br.com.nexfar.applicationtest.Dto.SearchTermDTO;
import br.com.nexfar.applicationtest.Services.ListAndReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ListingAndReviewControllers {

    @Autowired
    ListAndReviewService service;

    @GetMapping
    public ResponseEntity<?> getListings(Pageable page) {
        service.teste();
        return new ResponseEntity<>(" ", HttpStatus.OK);
    }

    @PostMapping("/search")
    public void searchListing(@RequestBody SearchTermDTO dto) {
//        return new ResponseEntity<>(service.searchTerm(dto), HttpStatus.CREATED);
    }
}