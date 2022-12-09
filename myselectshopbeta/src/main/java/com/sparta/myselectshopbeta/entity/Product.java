package com.sparta.myselectshopbeta.entity;

import com.sparta.myselectshopbeta.dto.ProductMypriceRequestDto;
import com.sparta.myselectshopbeta.dto.ProductRequestDto;
import com.sparta.myselectshopbeta.naver.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID가 자동으로 생성 및 증가합니다.
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    @Column(nullable = false)
    private Long userId; //JWT를 사용하여 관심상품 조회하기

    @ManyToMany
    private List<Folder> folderList = new ArrayList<>();

    public Product(ProductRequestDto requestDto, Long userId) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
        this.userId = userId; //JWT를 사용하여 관심상품 조회하기
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myprice = requestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }

    public void addFolder(Folder folder) {
        this.folderList.add(folder);
    }

}