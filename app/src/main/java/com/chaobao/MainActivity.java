package com.chaobao;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initState();
    }

    private void initState(){
        //系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            LinearLayout linear = (LinearLayout) findViewById(R.id.main_status_bar);

            linear.setVisibility(View.VISIBLE);

            int mStatusH = getStatusBarHeight();

            //动态设置高度
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)linear.getLayoutParams();
            params.height = mStatusH;
            linear.setLayoutParams(params);
        }
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelOffset(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
