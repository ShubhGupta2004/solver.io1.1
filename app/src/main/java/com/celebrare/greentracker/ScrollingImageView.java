package com.celebrare.greentracker;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public class ScrollingImageView extends AppCompatImageView {
    private final Matrix transformationMatrix = new Matrix();
    private float scalingFactor = 1.0f;
    private float verticalTranslation = 0.0f;
    private float maxVerticalTranslation = 0.0f;
    private ValueAnimator scrollAnimator;

    private static final int ANIMATION_DURATION = 45000;

    public ScrollingImageView(Context context) {
        super(context);
        initialize();
    }

    public ScrollingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ScrollingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    private void initialize() {
        setScaleType(ScaleType.MATRIX);
        scrollAnimator = ValueAnimator.ofFloat(0, 1);
        scrollAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scrollAnimator.setDuration(ANIMATION_DURATION);
        scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                verticalTranslation = fraction * maxVerticalTranslation;
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        updateImagePosition();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startScrollAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScrollAnimation();
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        updateImagePosition();
        super.onDraw(canvas);
    }

    private void updateImagePosition() {
        transformationMatrix.reset();
        Drawable drawable = getDrawable();

        if (drawable == null)
            return;

        float viewWidth = getWidth();
        float viewHeight = getHeight();
        float drawableWidth = drawable.getIntrinsicWidth();
        float drawableHeight = drawable.getIntrinsicHeight();

        // Calculate the scaling factors for width and height
        float scaleX = viewWidth / drawableWidth;
        float scaleY = viewHeight / drawableHeight;

        // Calculate the final scale factor (maintaining aspect ratio)
        scalingFactor = Math.max(scaleX, scaleY);

        // Calculate the maximum vertical translation (scroll range)
        maxVerticalTranslation = Math.max(0, (drawableHeight * scalingFactor) - viewHeight);

        // Apply the transformation matrix
        transformationMatrix.setScale(scalingFactor, scalingFactor);
        transformationMatrix.postTranslate(0, -verticalTranslation);
        setImageMatrix(transformationMatrix);
    }

    private void startScrollAnimation() {
        if (scrollAnimator != null && !scrollAnimator.isRunning())
            scrollAnimator.start();
    }

    private void stopScrollAnimation() {
        if (scrollAnimator != null && scrollAnimator.isRunning())
            scrollAnimator.cancel();
    }
}

