package com.example.hong.repository;

import com.example.hong.entity.Item;
import com.example.hong.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
    ItemImg findByItem_id(Long id);

    List<ItemImg> findByItemIdOrderByIdAsc(Item itemId);

    ItemImg findByItemIdAndRepImgYn(Long itemId, String repImgYn);
}
