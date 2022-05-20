package com.tavares.graphqlsample.helper.map;

import com.tavares.graphqlsample.dto.ListingInput;
import com.tavares.graphqlsample.entity.Listing;
import com.tavares.graphqlsample.entity.TradeType;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapListing {

    public Listing mapInputToListing(ListingInput listingInput, TradeType tradeType) {
        Listing listing = new Listing();

        listing.setTitle(listingInput.getTitle());
        listing.setDescription(listingInput.getDescription());
        listing.setPrice(listingInput.getPrice());
        listing.setCurrency(listingInput.getCurrency());
        listing.setCreatedAt(new Date());
        listing.setTradeType(tradeType);

        return listing;
    }
}
