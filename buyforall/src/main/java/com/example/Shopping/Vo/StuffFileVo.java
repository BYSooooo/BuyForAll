package com.example.Shopping.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//상품 정보와 관련한 파일 정보가 담긴 Vo

@NoArgsConstructor
@Data
@AllArgsConstructor
public class StuffFileVo {

    int barcode; //어떤 상품의 파일인지 표기
    int fileNo; //파일 번호
    String fileNameOrigin;  //파일 원본 이름
    String fileNameSys; //파일 시스템 이름
    long fileSize;  //파일 사이즈
    
    StuffFileVo(String fileNameOrigin, String fileNameSys, long fileSize) {

    }
}
