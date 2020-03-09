package com.br.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name="item_code")
    private Integer itemCode;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="sale_code")
    private String saleCode;
}
