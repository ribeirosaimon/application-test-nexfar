package br.com.nexfar.applicationtest.Services;

import br.com.nexfar.applicationtest.Dto.PriceRangeDTO;
import br.com.nexfar.applicationtest.Dto.SearchTermDTO;
import br.com.nexfar.applicationtest.Dto.SortDTO;
import br.com.nexfar.applicationtest.Model.ListingAndReview;
import br.com.nexfar.applicationtest.Repository.ListingAndReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAndReviewService {
    @Autowired
    ListingAndReviewRepository repository;

    public void searchTerm(SearchTermDTO dto) {
        List<ListingAndReview> all = repository.findAll();
        List<ListingAndReview> teste = all.stream().filter(e -> e.getAddress().getMarket().equals(dto.getSearchTerm()))
                .filter(e -> (dto.getMinReview() != null) ? e.getReviews().size() > dto.getMinReview() : e.getReviews().size() > 0)
                .collect(Collectors.toList());
        for (ListingAndReview e : teste) {
            System.out.println(e);
        }

    }

    public void teste() {
        SearchTermDTO dto = new SearchTermDTO(null,
                null,
                new PriceRangeDTO(100D, 100D),
                2,
                new SortDTO("ASC", "price"));

        List<ListingAndReview> listBase = repository.findAll();
        List<ListingAndReview> listSearchTerm = searchTerm(listBase, dto.getSearchTerm());
        List<ListingAndReview> listMinReview = searchMinReview(listSearchTerm, dto.getMinReview());
        List<ListingAndReview> listPriceRange = searchPriceRange(listMinReview, dto.getPriceRange());
        List<ListingAndReview> listBedrooms = searchBedrooms(listPriceRange, dto.getTotalBedrooms());

        int i = 0;
        for (ListingAndReview e : listPriceRange) {
            if (e.getBathrooms() >= dto.getPriceRange().getMin()) {
                i++;
                System.out.println(String.valueOf(e.getPrice()) + "--->" + String.valueOf(i));
            } else {
                System.out.println(String.valueOf(e.getPrice()) + "ESSE DEU ERRO!");
            }

        }
        System.out.println(listPriceRange.size());
    }




    public List<ListingAndReview> searchTerm(List<ListingAndReview> list, String param) {
        if (param != null)
            return list.stream().filter(e -> e.getAddress().getMarket().equals(param)).collect(Collectors.toList());
        return list;
    }

    public List<ListingAndReview> searchMinReview(List<ListingAndReview> list, Long param) {
        if (param != null) return list.stream().filter(e -> e.getReviews().size() > param).collect(Collectors.toList());
        return list;
    }

    public List<ListingAndReview> searchPriceRange(List<ListingAndReview> list, PriceRangeDTO param) {
        List<ListingAndReview> collectionReturn = new ArrayList<>();

        // Se o parametro for null, retorna a mesma lista;
        if (param == null) return list;
        // Se o Valor minimo for maior que o valor maximo vai retornar um Erro

        else if (param.getMin() > param.getMax()) throw new RuntimeException("minimum value is greater than maximum value");
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

    public List<ListingAndReview> searchBedrooms(List<ListingAndReview> list, Integer param){
        if (param != null) return list.stream().filter(e -> e.getBathrooms() >= param).collect(Collectors.toList());
        return list;
    }

}
