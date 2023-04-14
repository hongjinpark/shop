package com.example.hong.service;

import com.example.hong.dto.ItemDto;
import com.example.hong.entity.Item;
import com.example.hong.entity.ItemImg;
import com.example.hong.repository.ItemImgRepository;
import com.example.hong.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    //상품 조회
    public Item selectItem(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
    }

    //상품 등록
    @Transactional
    public Item createItem(ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception {

        Item item=itemDto.toEntity();



        //이미지 등록
        for(int i=0; i<itemImgFileList.size(); i++) {

            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);
            if(i == 0)
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return itemRepository.save(item);
    }

    //상품 수정
    @Transactional
    public Item updateItem(Long id, ItemDto itemDto, List<MultipartFile> itemImgFileList) throws Exception {

        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다. id=" + id));

        item.updateItem(itemDto.getItemName(),itemDto.getPrice(),itemDto.getStockNumber(), itemDto.getItemDetail());

        List<Long> itemImgIds = itemDto.getItemImgIds();

        //이미지 등록
        for (int i = 0; i < itemImgFileList.size(); i++) {

            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }

        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

}
