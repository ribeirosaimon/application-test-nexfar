package br.com.nexfar.applicationtest.Controllers;

import br.com.nexfar.applicationtest.Dto.SearchTermDTO;
import br.com.nexfar.applicationtest.Services.ListAndReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ListingAndReviewControllers {

    @Autowired
    ListAndReviewService service;


    @PostMapping("search")
    public ResponseEntity<?> searchListing(@RequestBody SearchTermDTO dto) {
        return ResponseEntity.ok(service.searchAirbnb(dto));
    }
}
