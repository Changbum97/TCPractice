package com.study.tcpractice.domain.entity;

import com.study.tcpractice.domain.dto.ItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    @OneToMany(mappedBy = "item")
    private List<Order> orders;

    public void edit(ItemDto itemDto) {
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
    }
}
