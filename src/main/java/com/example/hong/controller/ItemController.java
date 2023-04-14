package com.example.hong.controller;

import com.example.hong.dto.ItemDto;
import com.example.hong.entity.Item;
import com.example.hong.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/item")
public class ItemController {
    private final ItemService itemService;

    //상품조회
    @GetMapping("/{itemId}")
    public Item itemDtl(@PathVariable Long itemId) {
        return itemService.selectItem(itemId);
    }

    //상품등록
    @PostMapping("/new")
    public Item newItem(@RequestPart ItemDto itemDto, @RequestPart List<MultipartFile> itemImgFileList) throws Exception {

        return itemService.createItem(itemDto, itemImgFileList);
    }

    //상품 수정
    @PutMapping("/update/{itemId}")
    public Item updateItem(@PathVariable Long itemId, @RequestBody ItemDto itemDto, @RequestParam List<MultipartFile> itemImgFileList) throws Exception {

        return itemService.updateItem(itemId, itemDto, itemImgFileList);
    }

    //상품 삭제
    @DeleteMapping("/delete/{itemId}")
    public void deleteItem(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
    }

}
