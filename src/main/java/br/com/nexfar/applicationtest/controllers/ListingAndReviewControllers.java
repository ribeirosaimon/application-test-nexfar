package br.com.nexfar.applicationtest.controllers;

import br.com.nexfar.applicationtest.dto.ReturnDTO;
import br.com.nexfar.applicationtest.dto.SearchTermDTO;
import br.com.nexfar.applicationtest.services.ListAndReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class ListingAndReviewControllers {

    @Autowired
    ListAndReviewService service;

    @PostMapping("/search")
    public ResponseEntity<List<ReturnDTO>> searchAirbnb(@RequestBody SearchTermDTO dto) {
        return ResponseEntity.ok(service.searchByQueryString(dto));
    }

}
