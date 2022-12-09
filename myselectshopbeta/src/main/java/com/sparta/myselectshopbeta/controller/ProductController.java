package com.sparta.myselectshopbeta.controller;

import com.sparta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.sparta.myselectshopbeta.dto.ProductRequestDto;
import com.sparta.myselectshopbeta.dto.ProductResponseDto;
import com.sparta.myselectshopbeta.entity.Product;
import com.sparta.myselectshopbeta.entity.UserRoleEnum;
import com.sparta.myselectshopbeta.security.UserDetailsImpl;
import com.sparta.myselectshopbeta.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 관심 상품 등록하기
    @Secured(UserRoleEnum.Authority.ADMIN)
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 응답 보내기
        return productService.createProduct(requestDto, userDetails.getUser());
    }

    // 관심 상품 조회하기
    @GetMapping("/products")
    public Page<Product> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        // 응답 보내기
        return productService.getProducts(userDetails.getUser(), page-1, size, sortBy, isAsc);
    }

    // 관심 상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 응답 보내기 (업데이트된 상품 id)
        return productService.updateProduct(id, requestDto, userDetails.getUser());
    }

    // 상품에 폴더 추가
    @PostMapping("/products/{productId}/folder")
    public Long addFolder(
            @PathVariable Long productId,
            @RequestParam Long folderId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Product product = productService.addFolder(productId, folderId, userDetails.getUser());
        return product.getId();
    }

}