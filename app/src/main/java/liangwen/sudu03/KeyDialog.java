package liangwen.sudu03;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import  java.lang.Math.*;
import  liangwen.sudu03.Sudu.*;

/**
 * Created by lenovo on 2015/7/29.
 */
//该类用来实现自定义对话框功能
public class KeyDialog extends Dialog {
    //保存按钮的对象
    private final View key[]=new View[9];
    private final int used[];
    private Sudu sudu = null ;
//构造函数第二个参数保存当前单元格已经使用过的数字
    public KeyDialog(Context context, int[] used,Sudu sudu) {
        super(context);
        this.used = used;
        this.sudu = sudu;
    }
    //当一个对话框第一次显示的时候会调用onCreate
    @Override
    public void onCreate(Bundle savedInstanceState){

        setTitle("input number");
        setContentView(R.layout.keypad);
        findview();
        //
        //把不可用的数字按钮 设为不可见
        for(int i = 0 ; i < used.length ; i ++)
        {
            if(used[i] != 0)
            {
                //设置不可用的按钮  可见度： 不可见
                key[ used[i]-1 ].setVisibility(View.INVISIBLE);
            }
        }
        //为按钮设置监听器
        setKeyListeners();
        super.onCreate(savedInstanceState);
    }
    public void findview(){

        key[0]=findViewById(R.id.keypad_1);
        key[1]=findViewById(R.id.keypad_2);
        key[2]=findViewById(R.id.keypad_3);
        key[3]=findViewById(R.id.keypad_4);
        key[4]=findViewById(R.id.keypad_5);
        key[5]=findViewById(R.id.keypad_6);
        key[6]=findViewById(R.id.keypad_7);
        key[7]=findViewById(R.id.keypad_8);
        key[8]=findViewById(R.id.keypad_9);
    }
    private void setKeyListeners()
    {
        for(int i = 0 ; i < key.length ; i ++)
        {
            final int t = i+1 ;

            key[i].setOnClickListener(new View.OnClickListener(){

                public void onClick(View v)
                {
                    returnResult(t);
                }
            });
        }
    }
    private void   returnResult(int tile) {

       sudu.setSelectedTile(tile);
        dismiss(); // 把对话框 从屏幕上移除
    }

}
