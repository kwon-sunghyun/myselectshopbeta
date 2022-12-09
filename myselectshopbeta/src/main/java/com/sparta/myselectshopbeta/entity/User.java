package com.sparta.myselectshopbeta.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nullable: null 허용 여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //열거??, EnumType에는 총 두 가지 타입이 있다., EnumType.STRING : 각 Enum 이름을 컬럼에 저장한다. ex) G, PG, PG13.., EnumType.ORDINAL : 각 Enum에 대응되는 순서를 칼럼에 저장한다. ex) 0, 1, 2..
    private UserRoleEnum role;

    @OneToMany // N:1 양방향관계에서 1쪽에서 N쪽으로의 방향성 또는 1:N 단반향에서는  컬렉션을 참조하므로 Set, List, Map을 사용할 수 있다., 일대다, 회원 한 명이 폴더를 여러 개 작성할 수 있으므로 회원(Member) 기준으로 @OneToMany를 선언합니다.
    List<Folder> folders = new ArrayList<>();

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}