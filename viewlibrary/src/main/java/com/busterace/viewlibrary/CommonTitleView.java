package com.busterace.viewlibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** 通用的TitleView
 * Created by wudq on 2016/9/16.
 */
public class CommonTitleView extends RelativeLayout {
    private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private TextView tvTitleLeft;
    private TextView tvTitleMiddle;
    private TextView tvTitleRight;
    private String middleText;
    private String leftText;
    private String rightText;
    private Drawable leftDrawable;
    private Drawable rightDrawable;
    private int leftDrawableId;
    private int rightDrawableId;

    public CommonTitleView(Context context) {
        super(context);
        initView();
    }

    public CommonTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        middleText = attrs.getAttributeValue(NAMESPACE, "middle_text");
        leftText = attrs.getAttributeValue(NAMESPACE, "left_text");
        rightText = attrs.getAttributeValue(NAMESPACE, "right_text");
        leftDrawableId = attrs.getAttributeResourceValue(NAMESPACE, "left_drawable", -1);
        rightDrawableId = attrs.getAttributeResourceValue(NAMESPACE, "right_drawable", -1);
        initView();
    }

    public CommonTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommonTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView(){
        View.inflate(getContext(),R.layout.common_title_view,this);
        tvTitleLeft = (TextView) findViewById(R.id.tv_title_left);
        tvTitleMiddle = (TextView) findViewById(R.id.tv_title_middle);
        tvTitleRight = (TextView) findViewById(R.id.tv_title_right);


        tvTitleLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
    }

    public TextView getTvTitleLeft() {
        return tvTitleLeft;
    }

    public TextView getTvTitleMiddle() {
        return tvTitleMiddle;
    }

    public TextView getTvTitleRight() {
        return tvTitleRight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tvTitleMiddle.setText(middleText);
        tvTitleLeft.setText(leftText);
        if (leftDrawableId != -1){
            leftDrawable = getResources().getDrawable(leftDrawableId);
            leftDrawable.setBounds(0,0,leftDrawable.getMinimumWidth(),leftDrawable.getMinimumHeight());
            tvTitleLeft.setCompoundDrawables(leftDrawable,null,null,null);
        }
        if (rightDrawableId != -1){
            rightDrawable = getResources().getDrawable(rightDrawableId);
            rightDrawable.setBounds(0,0,rightDrawable.getMinimumWidth(),rightDrawable.getMinimumHeight());
            tvTitleRight.setCompoundDrawables(null,null,rightDrawable,null);
        }
        tvTitleRight.setText(rightText);
    }
}
