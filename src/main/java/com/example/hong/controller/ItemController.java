package com.example.hong.controller;

import com.example.hong.dto.ItemDto;
import com.example.hong.entity.Item;
import com.example.hong.mapper.ItemMapper;
import com.example.hong.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/item")
public class ItemController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    Map<String, Object> resultMap = new HashMap<>();

    //상품조회
    @GetMapping("/{itemId}")
    public ItemDto itemDtl(@PathVariable Long itemId) {

        return itemService.selectItem(itemId);
    }

    //전체 상품조회
    @GetMapping
    public ResponseEntity<?> findAllItem() {


        List<Item> items = itemMapper.getAllItem();
        resultMap.put("items", items);

        return new ResponseEntity<>(items, HttpStatus.OK);
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
