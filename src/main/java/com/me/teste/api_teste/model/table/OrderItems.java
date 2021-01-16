package com.me.teste.api_teste.model.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "quantity")
    private Long quantity;

    @Getter
    @Setter
    @Column(name = "unit_price")
    private Double unitPrice;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Orders order;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "fk_item")
    private Items item;

}
