package com.example.hong.service;

import com.example.hong.dto.ItemDto;
import com.example.hong.entity.Item;
import com.example.hong.repository.ItemImgRepository;
import com.example.hong.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;

    public Item selectItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
    }
    @Transactional
    public Item createItem(ItemDto itemDto){

        Item item=itemDto.toEntity();

        return itemRepository.save(item);
    }

    @Transactional
    public Item updateItem(Long id,ItemDto itemDto){
        Item item=itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));
        String img=itemImgRepository.findByItem_id(id).getImgUrl(); //file 구현후 사용
        item.updateItem(img,itemDto.getItemName(),itemDto.getPrice(),itemDto.getStockNumber(), itemDto.getItemDetail());
        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

}
