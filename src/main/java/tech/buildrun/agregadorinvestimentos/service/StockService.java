package tech.buildrun.agregadorinvestimentos.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.buildrun.agregadorinvestimentos.controller.dto.CreateStockDto;
import tech.buildrun.agregadorinvestimentos.entity.Stock;
import tech.buildrun.agregadorinvestimentos.repository.StockRepository;

@Service
public class StockService {

    private final StockRepository stockRepository;
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createStock(@RequestBody CreateStockDto createStockDto) {

        var stock = new Stock(
                createStockDto.stockId(),
                createStockDto.description()
        );
        stockRepository.save(stock);

    }


}
