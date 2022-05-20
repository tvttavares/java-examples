package com.tavares.graphqlsample.service.listing;

import com.tavares.graphqlsample.dto.ListingInput;
import com.tavares.graphqlsample.entity.Listing;
import com.tavares.graphqlsample.entity.TradeType;
import com.tavares.graphqlsample.helper.map.MapListing;
import com.tavares.graphqlsample.repository.ListingRepository;
import com.tavares.graphqlsample.repository.TradeTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListingServiceImpl implements ListingService {

    private static final Integer DEFAULT_SKIP = 0;
    private static final Integer DEFAULT_LIMIT = 4;
    private final ListingRepository listingRepository;
    private final TradeTypeRepository tradeTypeRepository;
    private final MapListing mapListing;

    @Override
    public Listing add(ListingInput listingInput) {
        TradeType tradeType = findTradeTypeById(listingInput.getTradeTypeId());
        Listing listing = mapListing.mapInputToListing(listingInput, tradeType);
        return listingRepository.save(listing);
    }

    @Override
    public Listing getById(Long id) {
        return listingRepository.findById(id).get();
    }

    @Override
    public List<Listing> get(Integer skip, Integer limit) {
        return listingRepository.get(skip == null ? DEFAULT_SKIP : skip, limit == null ? DEFAULT_LIMIT : limit);
    }

    private TradeType findTradeTypeById(Long id) {
        return tradeTypeRepository.findById(id).get();
    }
}
