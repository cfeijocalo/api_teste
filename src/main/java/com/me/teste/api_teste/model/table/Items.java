package com.me.teste.api_teste.model.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "items")
public class Items implements IModel {

    @Getter
    @Setter
    @Id
    @SequenceGenerator(name = "items_sequence", sequenceName = "items_seq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "items_sequence")
    private Long id;

    @Getter
    @Setter
    @Column(name = "description", unique = true)
    private String description;

}
