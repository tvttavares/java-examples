package com.tavares.graphqlsample.service.tradetype;

import com.tavares.graphqlsample.dto.TradeTypeInput;
import com.tavares.graphqlsample.entity.TradeType;

import java.util.List;

public interface TradeTypeService {

    TradeType add(TradeTypeInput tradeTypeInput);

    TradeType getById(Long id);

    List<TradeType> get(Integer skip, Integer limit);
}
