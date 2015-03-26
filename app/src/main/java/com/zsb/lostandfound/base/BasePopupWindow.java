package com.zsb.lostandfound.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
/**
 * 自定义popWindow
 * Created by zsb on 2015/3/25.
 */
public abstract class BasePopupWindow extends PopupWindow{
    protected View mContentView;

    protected onSubmitClickListener mOnSubmitClickListener;
    public BasePopupWindow() {
        super();
    }

    public BasePopupWindow(Context context, AttributeSet attrs,
                           int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public BasePopupWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BasePopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePopupWindow(Context context) {
        super(context);
    }

    public BasePopupWindow(int width, int height) {
        super(width, height);
    }

    public BasePopupWindow(View contentView, int width, int height,
                           boolean focusable) {
        super(contentView, width, height, focusable);
    }

    public BasePopupWindow(View contentView) {
        super(contentView);
    }

    @SuppressWarnings("deprecation")
    public BasePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height, true);
        mContentView = contentView;
        setBackgroundDrawable(new BitmapDrawable());
        setTouchable(true);
        setOutsideTouchable(true);
        setTouchInterceptor(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
        initViews();
        initEvents();
        init();
    }

    public abstract void initViews();

    public abstract void initEvents();

    public abstract void init();

    public View findViewById(int id) {
        return mContentView.findViewById(id);
    }

    /**
     * 添加确认单击监听
     *
     * @param l
     */
    public void setOnSubmitClickListener(onSubmitClickListener l) {
        mOnSubmitClickListener = l;
    }

    protected interface onSubmitClickListener{
        void onClick();
    }
}
