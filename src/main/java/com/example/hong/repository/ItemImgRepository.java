package com.example.hong.repository;

import com.example.hong.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImgRepository extends JpaRepository<ItemImg,Long> {
    ItemImg findByItem_id(Long id);
}
