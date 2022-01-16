package br.com.nexfar.applicationtest.ServiceTest;

import br.com.nexfar.applicationtest.dto.PriceRangeDTO;
import br.com.nexfar.applicationtest.dto.SearchTermDTO;
import br.com.nexfar.applicationtest.dto.SortDTO;
import br.com.nexfar.applicationtest.services.ListAndReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class ListAndReviewServiceTest {
    static final String SEARCH_TERM = "portugal";
    static final Long MIN_REVIEW = 50L;
    static final PriceRangeDTO PRICE_RANGE = new PriceRangeDTO(100D, 200D);
    static final Integer BEDROOMS = 1;
    static final SortDTO SORT_VALUE = new SortDTO("asc", "price");

    @Autowired
    MockMvc mvc;

    @MockBean
    ListAndReviewService service;

    @Test
    @DisplayName("If use searchByQueryString function")
    public void searchByQueryStringTest() throws JsonProcessingException {
        SearchTermDTO searchTermDTO = new SearchTermDTO(SEARCH_TERM, MIN_REVIEW, PRICE_RANGE, BEDROOMS, SORT_VALUE);
        service.searchByQueryString(searchTermDTO);
        Mockito.verify(service, Mockito.times(1)).searchByQueryString(searchTermDTO);
    }
}
