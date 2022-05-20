package com.tavares.graphqlsample.helper.map;

import com.tavares.graphqlsample.dto.TradeTypeInput;
import com.tavares.graphqlsample.entity.TradeType;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapTradeType {

    public TradeType mapInputToTradeType(TradeTypeInput tradeTypeInput) {
        TradeType tradeType = new TradeType();

        tradeType.setName(tradeTypeInput.getName());
        tradeType.setCreatedAt(new Date());

        return tradeType;
    }
}
