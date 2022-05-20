package com.tavares.graphqlsample.resolver.tradetype;

import com.tavares.graphqlsample.entity.TradeType;
import com.tavares.graphqlsample.service.tradetype.TradeTypeService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TradeTypeQueryResolver implements GraphQLQueryResolver {

    private final TradeTypeService tradeTypeService;

    public TradeType getTradeTypeById(Long id) {
        return tradeTypeService.getById(id);
    }

    public List<TradeType> getTradeTypes(Integer skip, Integer limit) {
        return tradeTypeService.get(skip, limit);
    }
}