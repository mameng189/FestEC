package com.matt.latter.ec.main;

import android.graphics.Color;

import com.matt.latter.delegates.bottom.BaseBottomDelegate;
import com.matt.latter.delegates.bottom.BottomItemDelegate;
import com.matt.latter.delegates.bottom.BottomTabBean;
import com.matt.latter.delegates.bottom.ItemBuilder;
import com.matt.latter.ec.main.index.IndexDelegate;
import com.matt.latter.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/10/18.
 */

public class EcBottomDelegate extends BaseBottomDelegate{
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final  LinkedHashMap<BottomTabBean,BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
