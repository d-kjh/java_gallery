package kr.co.wikibook.gallery.order;

import kr.co.wikibook.gallery.item.ItemMapper;
import kr.co.wikibook.gallery.item.model.ItemGetRes;
import kr.co.wikibook.gallery.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;

    public int saveOrder(OrderPostReq req){
        // 상품 정보 DB로 부터 가져온다.
        List<ItemGetRes> result = itemMapper.findAllByIdIn(req.getItemIds());
        return 1;
    }
}
