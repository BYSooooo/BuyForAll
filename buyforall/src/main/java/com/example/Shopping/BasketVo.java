package com.example.Shopping;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//장바구니의 Vo Class
@NoArgsConstructor
@AllArgsConstructor
public class BasketVo {

    ArrayList<SelectedStuffVo> selectedStuff;   //선택 상품의 Vo Class
    int amountCount;        //구매 총액
    
}
