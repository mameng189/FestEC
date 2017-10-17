package com.matt.latter.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

import com.matt.latter.delegates.LatterDelegate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/16.
 */

public abstract class BaseBottomDelegate extends LatterDelegate{
    //存储所有delegate
    private final ArrayList<BottomItemDelegate> ITEM_DELEGATE = new ArrayList<>();
    //存储所有bean
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    //存储映射
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentDelegate = 0;//当前页面
    private int mIndexDelegate = 0;//索引
    private int mClickedColor = Color.RED;
    public abstract  LinkedHashMap<BottomTabBean,BottomItemDelegate> setItems(ItemBuilder builder);
    public abstract int setIndexDelegate();
    @ColorInt
    public abstract int setClickedColor();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setClickedColor()!=0){
            mClickedColor = setClickedColor();
        }
        final  ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean,BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean,BottomItemDelegate> item:ITEMS.entrySet()){
            final BottomTabBean key = item.getKey();
            final BottomItemDelegate value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATE.add(value);
        }
    }
}

