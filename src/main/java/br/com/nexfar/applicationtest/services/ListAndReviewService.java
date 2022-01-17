package br.com.nexfar.applicationtest.services;

import br.com.nexfar.applicationtest.comparable.ComparatorPrice;
import br.com.nexfar.applicationtest.dto.PriceRangeDTO;
import br.com.nexfar.applicationtest.dto.ReturnDTO;
import br.com.nexfar.applicationtest.dto.SearchTermDTO;
import br.com.nexfar.applicationtest.dto.SortDTO;
import br.com.nexfar.applicationtest.model.ListingAndReview;
import br.com.nexfar.applicationtest.repository.ListingAndReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAndReviewService {
    @Autowired
    ListingAndReviewRepository repository;

    public List<ReturnDTO> searchByQueryString(SearchTermDTO dto) {
        List<ListingAndReview> completQuery;
        // searchTerm é obrigatorio
        SearchTermDTO searchTermDTO = formatDto(dto);

        // Se o Valor minimo for maior que o valor maximo vai retornar um Erro
        if (dto.getPriceRange() != null && dto.getPriceRange().getMax() != null) {
            if (dto.getPriceRange().getMin() > dto.getPriceRange().getMax())
                throw new RuntimeException("minimum value is greater than maximum value");
        }
        if (dto.getPriceRange().getMax() == null) {
            completQuery = repository.newQueryAirbnbWithoughtMax(dto.getSearchTerm(),
                    dto.getMinReview(),
                    dto.getPriceRange().getMin(),
                    dto.getTotalBedrooms());
        } else {
            completQuery = repository.newQueryAirbnb(dto.getSearchTerm(),
                    dto.getMinReview(),
                    dto.getPriceRange().getMin(),
                    dto.getPriceRange().getMax(),
                    dto.getTotalBedrooms());
        }


        if (dto.getSort().getProperty().equalsIgnoreCase("price")) {
            if (dto.getSort().getOrder().equalsIgnoreCase("des")) {
                completQuery.sort(new ComparatorPrice().reversed());
            } else {
                completQuery.sort(new ComparatorPrice());
            }
        } else if (dto.getSort().getProperty().equalsIgnoreCase("review")) {
            if (dto.getSort().getOrder().equalsIgnoreCase("des")) {
                // e tambem pelas datas ( fiz por uma função pois tem valores nulos)
                completQuery = sortedDate(completQuery, false);
            } else {
                completQuery = sortedDate(completQuery, true);
            }
        }
        return formatList(completQuery);
    }


    public SearchTermDTO formatDto(SearchTermDTO dto) {
        //Caso o Search Term for null preciso mudar o parametro para "" para que a query busque todos os valores
        if (dto.getSearchTerm() == null) dto.setSearchTerm("");
        if (dto.getMinReview() == null) dto.setMinReview(0L);
        // Se o priceRange for nulo preciso criar um novo
        if (dto.getPriceRange() == null) {
            dto.setPriceRange(new PriceRangeDTO(0D, null));
            // Se ele nao for nulo
        } else {
            // Preciso verificar se o getMax é nullo, se for preciso colocar 0
            if (dto.getPriceRange().getMin() == null) {
                dto.getPriceRange().setMin(0D);
            }
        }
        if (dto.getTotalBedrooms() == null) dto.setTotalBedrooms(0);
        // ASC e PRICE sera os valores default
        if (dto.getSort() == null) {
            dto.setSort(new SortDTO("asc", "price"));
        } else {
            if (dto.getSort().getProperty() == null) {
                dto.getSort().setProperty("price");
            } else if (dto.getSort().getOrder() == null) {
                dto.getSort().setOrder("asc");
            }
        }
        return dto;
    }


    public List<ListingAndReview> sortedDate(List<ListingAndReview> list, boolean reverse) {
        // ordenação pela data
        if (reverse) {
            return list
                    .stream()
                    .sorted(Comparator
                            .comparing(ListingAndReview::getLast_review, Comparator.nullsLast(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
        } else {
            return list
                    .stream()
                    .sorted(Comparator
                            .comparing(ListingAndReview::getLast_review, Comparator.nullsLast(Comparator.naturalOrder())))
                    .collect(Collectors.toList());
        }
    }

    public List<ReturnDTO> formatList(List<ListingAndReview> list) {
        // formata a lista para o DTO, coloquei esse print para caso queira confirmar os valores e data
        System.out.println("------------");
        list.forEach(System.out::println);
        System.out.println("Lista de Airbnb contem o tamanho de: " + list.size());
        return list.stream()
                .map(e -> new ReturnDTO(e.getAddress().getMarket(), e.getDescription(), e.getName()))
                .collect(Collectors.toList());
    }
}
