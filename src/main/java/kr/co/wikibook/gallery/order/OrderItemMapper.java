package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.order.model.OrderItemPostDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
    int save(OrderItemPostDto dto);

}
