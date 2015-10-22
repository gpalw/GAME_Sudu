package liangwen.sudu03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by lenovo on 2015/7/25.
 */
public class Sudu extends View {
   //单元格的宽度和高度
    private float width;
    private  float heigt;
    private Game game = new Game();
    private  int selectdX;
    private   int selectdY;

    public Sudu(Context context) {
        super(context);
    }


    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        this.heigt =h/9f;
        this.width=w/9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void onDraw(Canvas canvas){

        Paint backpaint = new Paint();
        backpaint.setColor(this.getResources().getColor(R.color.shudu_backgound));
        canvas.drawRect(0, 0, getWidth(), getHeight(), backpaint);


        Paint darkpaint = new Paint();
        backpaint.setColor(this.getResources().getColor(R.color.shudu_dark));


        Paint lightpaint = new Paint();
        backpaint.setColor(this.getResources().getColor(R.color.shudu_light));

        Paint hilitepaint = new Paint();
        backpaint.setColor(this.getResources().getColor(R.color.shudu_hilite));

        for (int i=0;i<9;i++)
        {
            canvas.drawLine(0, i*heigt, this.getWidth(), i*heigt,lightpaint);
           canvas.drawLine(0, i*heigt+1, this.getWidth(), i*heigt+1, hilitepaint);
//            canvas.drawLine(0, i*heigt+2, this.getWidth(), i*heigt+2, hilitepaint);
//            canvas.drawLine(0, i*heigt+3, this.getWidth(), i*heigt+3, hilitepaint);
//            canvas.drawLine(0, i*heigt+4, this.getWidth(), i*heigt+4, hilitepaint);
            canvas.drawLine(i*width,0, i*width, this.getHeight(), lightpaint);
            canvas.drawLine(i*width+1,0, i*width+1, this.getHeight(), hilitepaint);
        }
        for(int i = 0 ; i < 9 ; i ++)
        {
            if(i%3 != 0)
            {
                continue ;
            }
            canvas.drawLine(0, i*heigt, this.getWidth(), i*heigt, darkpaint);
            canvas.drawLine(0, i*heigt+1, this.getWidth(), i*heigt+1, hilitepaint);
            canvas.drawLine(0, i*heigt+2, this.getWidth(), i*heigt+2, hilitepaint);
            canvas.drawLine(0, i*heigt+3, this.getWidth(), i*heigt+3, hilitepaint);
            canvas.drawLine(0, i*heigt+4, this.getWidth(), i*heigt+4, hilitepaint);
            canvas.drawLine(0, i*heigt+5, this.getWidth(), i*heigt+5, hilitepaint);




            canvas.drawLine(i*width,0, i*width, this.getHeight(), darkpaint);
            canvas.drawLine(i*width+1,0, i*width+1, this.getHeight(), hilitepaint);
            canvas.drawLine(i*width+2,0, i*width+2, this.getHeight(), hilitepaint);
            canvas.drawLine(i*width+3,0, i*width+3, this.getHeight(), hilitepaint);
            canvas.drawLine(i*width+4,0, i*width+4, this.getHeight(), hilitepaint);
            canvas.drawLine(i*width+5,0, i*width+5, this.getHeight(), hilitepaint);

        }
        //设置在表格上显示的数字
        Paint numberPaint = new Paint();

        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE); //让其画出来的东西是 空的
        numberPaint.setTextSize(heigt * 0.75f); //设置字体大小
        numberPaint.setTextAlign(Paint.Align.CENTER); //让字体居中
        Paint.FontMetrics fm=numberPaint.getFontMetrics();
        float x = width/2f ;
        float y = heigt/2f-(fm.ascent+fm.descent)/2;

       //初始化数据
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                canvas.drawText(game.getTileString(i, j), i*width+x,j*heigt+y , numberPaint);
            }
        }
        canvas.drawText("1", 8 * width + x, y, numberPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction()!=MotionEvent.ACTION_DOWN){return super.onTouchEvent(event);}
        //获取 点击 哪个单元格的坐标
        selectdX =(int)(event.getX()/width);
        selectdY =(int)(event.getY()/heigt);
        int used[] = game.getUsedTilesByCoor(selectdX, selectdY);
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<used.length;i++)
        {
            sb.append(used[i]);
        }
        KeyDialog keyDialog = new KeyDialog(this.getContext(),used,this) ;
        keyDialog.show() ;
        return true;
    }

    public  void setSelectedTile(int tile){

        if (game.setTileIfValid(selectdX,selectdY,tile))
        {
            invalidate();
         }/* 重新绘制整个视图，也就相当于 重新生成一次该对象 */

    }
//    public Sudu() {
//        super(context);
//    }

//    public Sudu(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public Sudu(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }


//    public Sudu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
