package com.example.Shopping.Vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//선택된 상품이 장바구니에 옮겨지기 전의 Vo Class

@NoArgsConstructor
@AllArgsConstructor
public class SelectedStuffVo {

    int selectedBarcode;    //선택된 상품의 바코드
    int selectedCount;      //선택한 상품의 갯수
    
}
