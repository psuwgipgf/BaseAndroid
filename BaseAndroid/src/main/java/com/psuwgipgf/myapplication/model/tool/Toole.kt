package com.psuwgipgf.myapplication.model.tool

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Created by kevin on 18-1-25.
 */


fun toast(context: Context, str:String){
    val t =Toast.makeText(context,str,Toast.LENGTH_SHORT)
    t.setGravity(Gravity.TOP,0,0)
    t.show()
}