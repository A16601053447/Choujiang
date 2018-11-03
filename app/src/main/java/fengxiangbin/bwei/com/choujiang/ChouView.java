package fengxiangbin.bwei.com.choujiang;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 命运的尘 on 2018/11/3.
 */

public class ChouView extends View {
    private int mHeight;
    private int mWidth;
    private int x;
    private int y;
    private boolean mOnBall;
    private int mRadius = 90;

    public ChouView(Context context) {
        this(context,null);
    }

    public ChouView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChouView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override//测量
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取当前控件的宽高
        mWidth = this.getWidth();
        mHeight = this.getHeight();
        //获取屏幕的正中心点
        x = mWidth / 2;
        y = mHeight / 2;
    }

    @Override//绘制
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();//画笔
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);//设置图片
        canvas.drawBitmap(bitmap,x,y,paint);//放入图片

    }
    //手势监听器,可以得到用户手指在屏幕上滑动的坐标
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float newX = event.getX();
                float newY = event.getY();
                //进行判断,判断用户的手指是否按在了图上
                mOnBall  = isOnBall(newX,newY);
                Toast.makeText(getContext(), "用户的手指是否点到图上了吗？"+mOnBall, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                //只用用户点到图上时,我才让它动
                if (mOnBall){
                    x = (int) event.getX();
                    y = (int) event.getY();
                    //回调OnDrawer方法
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
    //,判断用户的手指是否按在了图上
    private boolean isOnBall(float newX, float newY) {
        //勾股定理的绝对值
        double sqrt = Math.sqrt((newX - x) * (newX - x) + (newY - y) * (newY - y));
        //判断绝对值是否小于等于半径
        if(sqrt <= mRadius){
            return true;
        }
        return false;
    }
}
