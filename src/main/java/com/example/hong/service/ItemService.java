package com.example.hong.service;

import com.example.hong.dto.*;
import com.example.hong.entity.Item;
import com.example.hong.entity.ItemImg;
import com.example.hong.repository.ItemImgRepository;
import com.example.hong.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    //상품 조회
    @Transactional(readOnly = true)
    public ItemDto selectItem(Long itemId) {

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(ItemImg itemImg : itemImgList) {

            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }
        //상품 카테고리 조회 추가
        for (CategoryDto category : categoryDtoList) {
            CategoryDto categoryDto = CategoryDto.of(category);
            categoryDtoList.add(categoryDto);
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemDto itemDto = ItemDto.of(item);
        itemDto.setItemImgDtoList(itemImgDtoList);
        itemDto.setCategoryDtoList(categoryDtoList);
        return itemDto;

       /* return itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);*/
    }

    //전체 상품조회
    public List<Item> selectAllItem() {
        return itemRepository.findAll();
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

        log.info("====size==={}",itemImgFileList.size());
        //이미지 등록
        if(itemImgFileList != null) {
            for (int i = 0; i < itemImgFileList.size(); i++) {

                itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
            }
        }

        return itemRepository.save(item);
    }

    @Transactional
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public List<MainItemDto> getAllItem(ItemSearchDto itemSearchDto) {

        List<MainItemDto> items = itemRepository.findAllItemAndImgUrl(itemSearchDto);

        return items;
    }

}
