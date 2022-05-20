package com.tavares.graphqlsample.resolver.tradetype;

import com.tavares.graphqlsample.dto.TradeTypeInput;
import com.tavares.graphqlsample.entity.TradeType;
import com.tavares.graphqlsample.service.tradetype.TradeTypeService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TradeTypeMutationResolver implements GraphQLMutationResolver {

    private final TradeTypeService tradeTypeService;

    public TradeType addTradeType(TradeTypeInput tradeTypeInput) {
        return tradeTypeService.add(tradeTypeInput);
    }
}
