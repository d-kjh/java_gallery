package kr.co.wikibook.gallery.item;

import kr.co.wikibook.gallery.item.model.ItemGetRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;

    public List<ItemGetRes> findAll(List<Integer> ids) {
        return itemMapper.findAllByIdIn(ids);
    }
}
