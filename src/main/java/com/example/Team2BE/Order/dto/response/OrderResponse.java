package com.example.Team2BE.Order.dto.response;

import com.example.Team2BE.Order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    List<Order> orderList;

    public OrderResponse(List<Order> orderList) {
        this.orderList = orderList;
    }
}
