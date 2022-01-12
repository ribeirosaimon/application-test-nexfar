package br.com.nexfar.applicationtest.Services;

import br.com.nexfar.applicationtest.Comparable.ComparatorPrice;
import br.com.nexfar.applicationtest.Dto.PriceRangeDTO;
import br.com.nexfar.applicationtest.Dto.ReturnDTO;
import br.com.nexfar.applicationtest.Dto.SearchTermDTO;
import br.com.nexfar.applicationtest.Dto.SortDTO;
import br.com.nexfar.applicationtest.Model.ListingAndReview;
import br.com.nexfar.applicationtest.Repository.ListingAndReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAndReviewService {
    @Autowired
    ListingAndReviewRepository repository;

    public List<ReturnDTO> searchAirbnb(SearchTermDTO dto) {

        // Poderia fazer uma stream gigante, porem seria dificil entendimento

        List<ListingAndReview> listBase = repository.findAll();
        List<ListingAndReview> listSearchTerm = searchTerm(listBase, dto.getSearchTerm());
        List<ListingAndReview> listMinReview = searchMinReview(listSearchTerm, dto.getMinReview());
        List<ListingAndReview> listPriceRange = searchPriceRange(listMinReview, dto.getPriceRange());
        List<ListingAndReview> finalList = searchBedrooms(listPriceRange, dto.getTotalBedrooms());

        if (dto.getSort() == null) {
            dto.setSort(new SortDTO("ASC", "PRICE"));
        } else if (dto.getSort().getOrder() == null) {
            dto.getSort().setOrder("ASC");
        } else if (dto.getSort().getProperty() == null) {
            dto.getSort().setProperty("PRICE");
        }

        if (dto.getSort().getProperty().equalsIgnoreCase("price")) {
            if (dto.getSort().getOrder().equalsIgnoreCase("DES")) {
                finalList.sort(new ComparatorPrice().reversed());
            } else {
                finalList.sort(new ComparatorPrice());
            }
        } else if (dto.getSort().getProperty().equalsIgnoreCase("review")) {
            if (dto.getSort().getOrder().equalsIgnoreCase("DES")) {
                finalList = sortedDate(finalList, false);
            } else {
                finalList = sortedDate(finalList, true);
            }
        }

        return formatList(finalList);
    }


    public List<ListingAndReview> searchTerm(List<ListingAndReview> list, String param) {
        if (param != null)
            return list
                    .stream()
                    .filter(e -> e.getAddress().getCountry().equalsIgnoreCase(param) ||
                            e.getDescription().toLowerCase().contains(param) ||
                            e.getName().toLowerCase().contains(param))
                    .collect(Collectors.toList());
        return list;
    }

    public List<ListingAndReview> searchMinReview(List<ListingAndReview> list, Long param) {
        if (param != null) return list.stream().filter(e -> e.getReviews().size() > param).collect(Collectors.toList());
        return list;
    }

    public List<ListingAndReview> searchPriceRange(List<ListingAndReview> list, PriceRangeDTO param) {
        // Se o parametro for null, retorna a mesma lista;
        if (param == null) return list;
            // Se o Valor minimo for maior que o valor maximo vai retornar um Erro

        else if (param.getMin() > param.getMax())
            throw new RuntimeException("minimum value is greater than maximum value");
            // se algum dos parametros não for null faça o filter
        else if (param.getMax() != null && param.getMin() != null) {
            return list.stream()
                    .filter(e -> e.getPrice() >= param.getMin())
                    .filter(e -> e.getPrice() <= param.getMax())
                    .collect(Collectors.toList());
            // se o preço maximo estiver presente faça o filtro
        } else if (param.getMax() != null) {
            return list.stream()
                    .filter(e -> e.getPrice() <= param.getMax())
                    .collect(Collectors.toList());
            // e se o preço minimo estiver presente faça o filtro
        } else {
            return list.stream()
                    .filter(e -> e.getPrice() >= param.getMin())
                    .collect(Collectors.toList());
        }
    }

    public List<ListingAndReview> searchBedrooms(List<ListingAndReview> list, Integer param) {
        if (param != null) return list.stream().filter(e -> e.getBedrooms() >= param).collect(Collectors.toList());
        return list;
    }

    public List<ListingAndReview> sortedDate(List<ListingAndReview> list, boolean reverse) {
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
                            .comparing(ListingAndReview::getLast_review, Comparator.nullsFirst(Comparator.reverseOrder())))
                    .collect(Collectors.toList());
        }
    }

    public List<ReturnDTO> formatList(List<ListingAndReview> list) {
        return list.stream()
                .map(e -> new ReturnDTO(e.getAddress().getMarket(), e.getDescription(), e.getName())).collect(Collectors.toList());
    }
}
