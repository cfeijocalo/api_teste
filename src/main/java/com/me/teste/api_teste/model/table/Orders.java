package com.me.teste.api_teste.model.table;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "orders")
public class Orders implements IModel {

    @Getter
    @Setter
    @Id
    private String id;

    @Getter
    @Setter
    @Column(name = "status")
    private String status;

    @Getter
    @Setter
    @Column(name = "total_price")
    private Integer totalPrice;

    @Getter
    @Setter
    @Column(name = "total_quantity")
    private Integer totalQuantity;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private List<OrderItems> items;

    @Getter
    @Setter
    @Column(name = "create_at")
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "modified_at")
    private Date modifiedAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    @PreUpdate
    void modifiedAt() {
        this.modifiedAt = new Date();
    }

}
