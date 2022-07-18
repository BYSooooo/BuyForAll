package com.example.Shopping.Vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//상품 정보를 담는 Vo Class

@NoArgsConstructor
@AllArgsConstructor
public class StuffVo {
    
    int barcode;    //상품 바코드 (PK)
    String stuffName;  //상품 이름
    int stuffPrice;     //상품 가격
    int[] fileNo;   //사용된 파일 list
    String stuffExplain;    //상품 설명
    int stuffStack;     //상품 재고

    MultipartFile file;
    List<MultipartFile> fileList;
    StuffFileVo sutffFile;
    List<StuffFileVo> stuffFiles;

    String cate_first; // 대분류
    String cate_se; //중분류
    String cate_thd; //소분류
    
    
}
