package com.example.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;

    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;

//    private HashMap param = new HashMap();
    private HashMap param = new HashMap();//避免没有传递参数时，param为空指针
}
