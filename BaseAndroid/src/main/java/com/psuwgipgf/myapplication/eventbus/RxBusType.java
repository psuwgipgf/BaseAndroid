package com.psuwgipgf.myapplication.eventbus;

import java.util.Objects;

/**
 * Created by psu on 2016/4/24.
 */
public class RxBusType {
    public static final int NETWORK_STATUS = 0x1;


    public int type = 0;

    public Objects obj;

    public RxBusType(int t) {
        type=t;
    }

}
