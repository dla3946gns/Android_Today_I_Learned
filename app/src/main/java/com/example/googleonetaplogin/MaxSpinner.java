package com.example.googleonetaplogin;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.widget.AppCompatSpinner;

import java.lang.reflect.Field;

public class MaxSpinner extends AppCompatSpinner {

    private android.widget.ListPopupWindow popupWindow;

    public MaxSpinner(Context context) {
        super(context);
    }

    public MaxSpinner(Context context, int mode) {
        super(context, mode);
    }

    public MaxSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        super.setAdapter(adapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            popupWindow = (android.widget.ListPopupWindow) popup.get(this);
            popupWindow.setHeight(100);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
