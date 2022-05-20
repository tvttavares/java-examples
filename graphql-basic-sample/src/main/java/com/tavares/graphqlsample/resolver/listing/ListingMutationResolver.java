package com.tavares.graphqlsample.resolver.listing;

import com.tavares.graphqlsample.dto.ListingInput;
import com.tavares.graphqlsample.entity.Listing;
import com.tavares.graphqlsample.service.listing.ListingService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListingMutationResolver implements GraphQLMutationResolver {

    private final ListingService listingService;

    public Listing addListing(ListingInput listingInput) {
        return listingService.add(listingInput);
    }
}
