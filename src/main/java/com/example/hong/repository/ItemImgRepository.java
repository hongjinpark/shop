package com.example.hong.repository;

import com.example.hong.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
    ItemImg findByItem_id(Long id);

    List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);

    ItemImg findByItemIdAndRepImgYn(Long itemId, String repImgYn);

    @Query(value = "select ii.id from ItemImg ii where ii.item.id = :id")
    List<Long> countById(Long id);
}
