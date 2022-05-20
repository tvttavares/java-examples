package com.tavares.graphqlsample.resolver.listing;

import com.tavares.graphqlsample.entity.Listing;
import com.tavares.graphqlsample.service.listing.ListingService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListingQueryResolver implements GraphQLQueryResolver {

    private final ListingService listingService;

    public Listing getListingById(Long id) {
        return listingService.getById(id);
    }

    public List<Listing> getListings(Integer skip, Integer limit) {
        return listingService.get(skip, limit);
    }
}
