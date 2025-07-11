package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.order.model.OrderDetailDto;
import kr.co.wikibook.gallery.order.model.OrderItemPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    int save(OrderItemPostDto dto);
    List<OrderDetailDto> findAllByOrderId(int orderId);

}
