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
        // searchTerm é obrigatorio
        if (dto.getSearchTerm() == null) throw new RuntimeException("Need SearchTerm");


        // Se o Valor minimo for maior que o valor maximo vai retornar um Erro
        if (dto.getPriceRange() != null) {
            if (dto.getPriceRange().getMin() == null && dto.getPriceRange().getMax() == null && dto.getPriceRange().getMin() > dto.getPriceRange().getMax())
                throw new RuntimeException("minimum value is greater than maximum value");
        }


        // Poderia fazer uma stream gigante, porem seria dificil entendimento
        List<ListingAndReview> listBase = repository.findListingAndReviewByAddress_Country(dto.getSearchTerm());
        List<ListingAndReview> listMinReview = searchMinReview(listBase, dto.getMinReview());
        List<ListingAndReview> listPriceRange = searchPriceRange(listMinReview, dto.getPriceRange());
        List<ListingAndReview> finalList = searchBedrooms(listPriceRange, dto.getTotalBedrooms());

        //Se caso o parametro sort estiver null, vai assumir "ASC" e "PRICE" como default
        if (dto.getSort() == null) {
            dto.setSort(new SortDTO("ASC", "PRICE"));
        } else if (dto.getSort().getOrder() == null) {
            dto.getSort().setOrder("ASC");
        } else if (dto.getSort().getProperty() == null) {
            dto.getSort().setProperty("PRICE");
        }

        // ordenei as listas por um comparator,
        if (dto.getSort().getProperty().equalsIgnoreCase("price")) {
            if (dto.getSort().getOrder().equalsIgnoreCase("des")) {
                finalList.sort(new ComparatorPrice().reversed());
            } else {
                finalList.sort(new ComparatorPrice());
            }
        } else if (dto.getSort().getProperty().equalsIgnoreCase("review")) {
            if (dto.getSort().getOrder().equalsIgnoreCase("des")) {
                // e tambem pelas datas ( fiz por uma função pois tem valores nulos)
                finalList = sortedDate(finalList, false);
            } else {
                finalList = sortedDate(finalList, true);
            }
        }
        // passei a lista para um DTO de resposta
        return formatList(finalList);
    }


    // Tinha Colocado um findall para poder pegar os parametros no Contry, Description ou Name, porem pela lentidão
    // nao foi viavel

//    public List<ListingAndReview> searchTerm(List<ListingAndReview> list, String param) {
//        if (param != null)
//            return list
//                    .stream()
//                    .filter(e -> e.getAddress().getCountry().equalsIgnoreCase(param) ||
//                            e.getDescription().toLowerCase().contains(param) ||
//                            e.getName().toLowerCase().contains(param))
//                    .collect(Collectors.toList());
//        return list;
//    }

    public List<ListingAndReview> searchMinReview(List<ListingAndReview> list, Long param) {
        // numeros de review deve ser maior que o numero do parametro enviado
        if (param != null)
            return list.stream().filter(e -> e.getNumber_of_reviews() > param).collect(Collectors.toList());
        return list;
    }

    public List<ListingAndReview> searchPriceRange(List<ListingAndReview> list, PriceRangeDTO param) {
        // Se o parametro for null, retorna a mesma lista;
        if (param == null) {
            return list;
        }


        // se algum dos parametros não for null faça o filter
        if (param.getMax() != null && param.getMin() != null) {
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
        // numero de quartos deve ser maior que o parametro
        if (param != null)
            return list.stream().filter(e -> (e.getBedrooms() == null ? 0 : e.getBedrooms()) >= param).collect(Collectors.toList());
        return list;
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
        return list.stream()
                .map(e -> new ReturnDTO(e.getAddress().getMarket(), e.getDescription(), e.getName()))
                .collect(Collectors.toList());
    }
}
