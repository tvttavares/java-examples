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
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }

    private TradeType findTradeTypeById(Long id) {
        return tradeTypeRepository.findById(id).get();
    }
}
