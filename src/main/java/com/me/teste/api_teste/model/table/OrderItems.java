package com.me.teste.api_teste.model.table;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.me.teste.api_teste.model.IModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItems implements IModel {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "order_items_sequence", sequenceName = "order_items_seq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "order_items_sequence")
    private Long id;

    @Getter
    @Setter
    @Column(name = "quantity")
    private Integer quantity;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_item")
    private Items item;

}
