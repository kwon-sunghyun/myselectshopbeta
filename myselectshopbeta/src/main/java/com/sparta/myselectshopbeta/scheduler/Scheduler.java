package com.sparta.myselectshopbeta.scheduler;

import com.sparta.myselectshopbeta.entity.Product;
import com.sparta.myselectshopbeta.naver.dto.ItemDto;
import com.sparta.myselectshopbeta.naver.service.NaverApiService;
import com.sparta.myselectshopbeta.repository.ProductRepository;
import com.sparta.myselectshopbeta.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final NaverApiService naverApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *") // 새벽 1시에 매번 자동으로 수행된다. 자세한건 구글링
    public void updatePrice() throws InterruptedException {
        log.info("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            // 1초에 한 상품 씩 조회합니다 (NAVER 제한)
            TimeUnit.SECONDS.sleep(1);

            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiService.searchItems(title);
            ItemDto itemDto = itemDtoList.get(0);

            // i 번째 관심 상품 정보를 업데이트합니다.
            Long id = product.getId();
            productService.updateBySearch(id, itemDto);
        }
    }
}