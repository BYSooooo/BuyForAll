package com.example.buyforall.Shopping;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//상품 정보를 담는 Vo Class

@NoArgsConstructor
@AllArgsConstructor
public class StuffVo {
    
    int barcode;    //상품 바코드 (PK)
    String stuffName;  //상품 이름
    String stuffPhotoSys;  //상품 사진 시스템 경로
    String stuffPhotoOrigin; //상품 사진 원본 경로
    int stuffPrice;     //상품 가격
    String stuffExplain;    //상품 설명
    int stuffStack;     //상품 재고
    
}
