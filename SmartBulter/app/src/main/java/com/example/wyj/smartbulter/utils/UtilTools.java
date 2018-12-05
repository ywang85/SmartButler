package com.example.wyj.smartbulter.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class UtilTools {
    public static void setFont(Context context, TextView textView) {
        Typeface fontType = Typeface.createFromAsset(context.getAssets(), "fonts/GreatVibes-Regular.otf");
        textView.setTypeface(fontType);
    }
}
