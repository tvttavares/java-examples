package com.tavares.graphqlsample.service.listing;

import com.tavares.graphqlsample.dto.ListingInput;
import com.tavares.graphqlsample.entity.Listing;

import java.util.List;

public interface ListingService {

    Listing add(ListingInput listingInput);

    Listing getById(Long id);

    List<Listing> get(Integer skip, Integer limit);
}