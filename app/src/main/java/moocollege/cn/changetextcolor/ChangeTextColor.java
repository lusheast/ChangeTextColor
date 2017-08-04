package moocollege.cn.changetextcolor;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import static moocollege.cn.changetextcolor.ChangeTextColor.Direction.FROME_LEFT_TO_RIGHT;

/**
 * Created by zsd on 2017/8/3 16:21
 * desc:仿今日头条首页顶部字体变色
 */


public class ChangeTextColor extends TextView {

    // 普通字体的画笔
    private Paint mNormalPaint;
    // 变色字体的画笔
    private Paint mChangePaint;
    // 当前的进度
    private float mCurrentProgress = 0.0f;

    private Direction mDirection = FROME_LEFT_TO_RIGHT;
    //方向
    public enum Direction {
        FROME_LEFT_TO_RIGHT, FROME_RIGHT_TO_LEFT
    }



    public ChangeTextColor(Context context) {
        this(context, null);
    }

    public ChangeTextColor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChangeTextColor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ChangeTextColor);
        int mNormalColor = array.getColor(R.styleable.ChangeTextColor_normalColor, getTextColors().getDefaultColor());
        int mChangeColor = array.getColor(R.styleable.ChangeTextColor_changeColor, getTextColors().getDefaultColor());
        array.recycle();


        //设置普通颜色画笔
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);//抗锯齿
        mNormalPaint.setColor(mNormalColor);
        mNormalPaint.setDither(true);// 防抖动
        mNormalPaint.setTextSize(getTextSize());

        //设置变化颜色画笔
        mChangePaint = new Paint();
        mChangePaint.setAntiAlias(true);//抗锯齿
        mChangePaint.setColor(mChangeColor);
        mChangePaint.setDither(true);// 防抖动
        mChangePaint.setTextSize(getTextSize());

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);不要用系统的  我们自己画
        //定一个中间值去画
        int midleValue = (int) (mCurrentProgress * getWidth());
        if (mDirection == FROME_LEFT_TO_RIGHT) {
            drawText(canvas, mChangePaint, 0, midleValue);
            drawText(canvas, mNormalPaint, midleValue, getWidth());
        } else {
            drawText(canvas, mChangePaint, getWidth() - midleValue, getWidth());
            drawText(canvas, mNormalPaint, 0, getWidth() - midleValue);
        }

    }

    /**
     * 绘制text
     *
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void drawText(Canvas canvas, Paint paint, int start, int end) {
        canvas.save();
        Rect react = new Rect(start, 0, end, getHeight());
        canvas.clipRect(react);
        String text = getText().toString();
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        //从什么地方开始画文字
        int x = getWidth() / 2 - bounds.width() / 2;
        //获得基线位置
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, x, baseLine, paint);
        canvas.restore();
    }

    /**
     * 设置方向
     *
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    /**
     * 设置进度
     *
     * @param currentProgress
     */
    public void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    public void setNormaleColor(int normalColor) {
        this.mNormalPaint.setColor(normalColor);
    }
}
