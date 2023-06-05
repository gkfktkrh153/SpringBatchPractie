package com.batch.app.product.entity;


import com.batch.app.base.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Product extends BaseEntity {
    private int price; // 권장 소비자 가격(할인이 없다면 이걸 이용함)
    private int salePrice; // 실제 판매에 쓸 금액
    private String name;
    private String makerShopName;
    private int wholesalePrice;
    private boolean isSoldOut; // 관련 옵션들이 전부 판매불능 상태일 때

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = ALL, orphanRemoval = true)
    private List<ProductOption> productOptions = new ArrayList<>();

    public void addOption(ProductOption option) {
        option.setProduct(this);
        option.setSalePrice(getPrice());
        option.setWholesalePrice(getWholesalePrice());
        option.setSalePrice(getSalePrice());
        productOptions.add(option);
    }
}