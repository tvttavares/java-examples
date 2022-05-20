package com.tavares.graphqlsample.service.tradetype;

import com.tavares.graphqlsample.dto.TradeTypeInput;
import com.tavares.graphqlsample.entity.TradeType;
import com.tavares.graphqlsample.helper.map.MapTradeType;
import com.tavares.graphqlsample.repository.TradeTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TradeTypeServiceImpl implements TradeTypeService {

    private static final Integer DEFAULT_SKIP = 0;
    private static final Integer DEFAULT_LIMIT = 4;
    private final TradeTypeRepository tradeTypeRepository;
    private final MapTradeType mapTradeType;

    @Override
    public TradeType add(TradeTypeInput tradeTypeInput) {
        TradeType tradeType = mapTradeType.mapInputToTradeType(tradeTypeInput);
        return tradeTypeRepository.save(tradeType);
    }

    @Override
    public TradeType getById(Long id) {
        return tradeTypeRepository.findById(id).get();
    }

    @Override
    public List<TradeType> get(Integer skip, Integer limit) {
        return tradeTypeRepository.get(skip == null ? DEFAULT_SKIP : skip, limit == null ? DEFAULT_LIMIT : limit);
    }
}
