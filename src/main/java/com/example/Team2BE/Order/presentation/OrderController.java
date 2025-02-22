package com.example.Team2BE.Order.presentation;


import com.example.Team2BE.Member.service.MemberService;
import com.example.Team2BE.Order.dto.request.OrderRequest;
import com.example.Team2BE.Order.dto.response.OrderResponse;
import com.example.Team2BE.Order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    public final OrderService orderService;
    public final MemberService memberService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<String> createOrderRequest(@RequestBody OrderRequest request) {
        orderService.createOrder(
                request.getMenu(),
                request.getCost(), request.getQuantity(),
                request.getIsPacked(),
                request.getMemberId()
        );
        return ResponseEntity.ok("주문이 성공적으로 생성되었습니다.");
    }

    // 주문 삭제
    @DeleteMapping("/{memberId}/{menu}/delete")
    public ResponseEntity<String> deleteOrderRequest(
            @PathVariable String memberId,
            @PathVariable String menu,
            @RequestParam Long quantity) {
        orderService.deleteOrder(menu, quantity, memberId);
        return ResponseEntity.ok("주문이 성공적으로 삭제되었습니다.");
    }

    // 주문 조회
    @GetMapping("/{memberId}/list")
    public ResponseEntity<OrderResponse> getOrderListRequest(@PathVariable String memberId) {
        OrderResponse orderResponse = new OrderResponse(orderService.getOrderList(memberId));
        return ResponseEntity.ok(orderResponse);
    }
}

