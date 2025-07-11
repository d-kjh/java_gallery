package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.cart.CartMapper;
import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    @Transactional
    public int saveOrder(OrderPostReq req, int memberId) {
        // 상품 정보 DB로 부터 가져온다.
        List<ItemGetRes> itemList = itemMapper.findAllByIdIn(req.getItemIds());
        log.info("itemList={}",itemList);
        // 총 구매가격 콘솔에 출력
        long sumPrice = 0;
        for (ItemGetRes item : itemList){
            sumPrice += item.getPrice() - ((item.getPrice() * item.getDiscountPer()) / 100);
            log.info("sumPrice={}",( item.getPrice() * item.getDiscountPer() )/ 100);
        }
            log.info("sumPrice={}", sumPrice);

        // OrderPostDto 객체화하고 데이터 넣기
        OrderPostDto orderPostDto = OrderPostDto.builder()
                .memberId(memberId)
                .name(req.getName())
                .address(req.getAddress())
                .payment(req.getPayment())
                .cardNumber(req.getCardNumber())
                .amount(sumPrice)
                .build();
        log.info("before - orderPostDto={}", orderPostDto);
        orderMapper.save(orderPostDto);
        log.info("after - orderPostDto={}", orderPostDto);
        // OrderItemPostDto 객체화 하시면서 데이터 넣어주세요.
        OrderItemPostDto orderItemPostDto =
                new OrderItemPostDto(orderPostDto.getOrderId(), req.getItemIds());

        orderItemMapper.save(orderItemPostDto);

        cartMapper.deleteByMemberId(memberId);

        return 1;
    }
    public List<OrderGetRes> findAllOrders(int memberId) {
        return orderMapper.findAllByMemberIdOrderByIdDesc(memberId);
    }

    public OrderDetailGetRes detail(OrderDetailGetReq req) {
        OrderDetailGetRes result = orderMapper.findByOrderIdAndMemberId(req);
        List<OrderDetailDto> items = orderItemMapper.findAllByOrderId(req.getOrderId());
        result.setItems(items);
        log.info("result={}",result);
        return result;
    }
}
