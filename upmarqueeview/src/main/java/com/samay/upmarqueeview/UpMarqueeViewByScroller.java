package com.samay.upmarqueeview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by shaohua.li on 7/28/16.
 */
public class UpMarqueeViewByScroller extends ViewGroup {

    private Scroller mScroller;
    private int topBorder;
    private int bottomBorder;
    private int childViewHeight;
    private ScrollerRunnanle scrollerRunnanle;

    public UpMarqueeViewByScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        scrollerRunnanle = new ScrollerRunnanle();
    }

    public void startAnimation() {
        mHandler.removeCallbacks(scrollerRunnanle);
        mHandler.postDelayed(scrollerRunnanle, 1000);
    }

    public void stopAnimation() {
        mHandler.removeCallbacks(scrollerRunnanle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                childView.layout(0, i * childView.getMeasuredHeight(), childView.getMeasuredWidth(), (i + 1) * childView.getMeasuredHeight());
            }
            topBorder = getChildAt(0).getTop();
            bottomBorder = getChildAt(getChildCount() - 1).getBottom();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            childViewHeight = childView.getHeight();
        }
    }

    private Handler mHandler = new Handler();

    private void scrollView() {
        if (getScrollY() < topBorder) {
            scrollTo(0, 0);
        } else if (getScrollY() > bottomBorder - getHeight()) {
            scrollTo(0, bottomBorder - getHeight());
        } else if (getScrollY() == bottomBorder - getHeight()) {
            scrollTo(0, 0);
        } else {
            mScroller.startScroll(0, getScrollY(), 0, childViewHeight, 1000);
            mScroller.startScroll(0, getScrollY(), 0, childViewHeight, 1000);
            postInvalidate();
        }
    }

    private class ScrollerRunnanle implements Runnable {

        @Override
        public void run() {
            Log.d("samay@@@", "ScrollerRunnanle");
            scrollView();
            invalidate();
            mHandler.postDelayed(this, 1000);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
