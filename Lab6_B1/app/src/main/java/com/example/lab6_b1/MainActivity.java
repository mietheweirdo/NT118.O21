package com.example.lab6_b1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnFadeInXml, btnFadeInCode, btnFadeOutXml, btnFadeOutCode,
            btnBlinkXml,
            btnBlinkCode, btnZoomInXml, btnZoomInCode, btnZoomOutXml,
            btnZoomOutCode, btnRotateXml,
            btnRotateCode, btnMoveXml, btnMoveCode, btnSlideUpXml, btnSlideUpCode,
            btnBounceXml,
            btnBounceCode, btnCombineXml, btnCombineCode;
    private ImageView ivUitLogo;
    private Animation.AnimationListener animationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewsByIds();
        initVariables();

        //HandleClickAnimationXML
        handleClickAnimationXML(btnFadeInXml, R.anim.anim_fade_in);
        handleClickAnimationXML(btnFadeOutXml, R.anim.anim_fade_out);
        handleClickAnimationXML(btnBlinkXml, R.anim.anim_blink);
        handleClickAnimationXML(btnZoomInXml, R.anim.anim_zoom_in);
        handleClickAnimationXML(btnZoomOutXml, R.anim.anim_zoom_out);
        handleClickAnimationXML(btnRotateXml, R.anim.anim_rotate);
        handleClickAnimationXML(btnMoveXml, R.anim.anim_move);
        handleClickAnimationXML(btnSlideUpXml, R.anim.anim_slide_up);
        handleClickAnimationXML(btnBounceXml, R.anim.anim_bounce);
        handleClickAnimationXML(btnCombineXml, R.anim.anim_combine);

        //HandleClickAnimationCode b2
        handleClickAnimationCode(btnFadeInCode, initFadeInAnimation());
        handleClickAnimationCode(btnFadeOutCode, initFadeOutAnimation());
        handleClickAnimationCode(btnBlinkCode, initBlinkAnimation());
        handleClickAnimationCode(btnZoomInCode, initZoomInAnimation());
        handleClickAnimationCode(btnZoomOutCode, initZoomOutAnimation());
        handleClickAnimationCode(btnRotateCode, initRotateAnimation());
        handleClickAnimationCode(btnMoveCode, initMoveAnimation());
        handleClickAnimationCode(btnSlideUpCode, initSlideUpAnimation());
        handleClickAnimationCode(btnBounceCode, initBounceAnimation());
        handleClickAnimationCode(btnCombineCode, initCombineAnimation());

        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_fade_in);

        animation.setAnimationListener(animationListener);

        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);

        ivUitLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(iNewActivity);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });
    }
    private void handleClickAnimationCode(Button btn, final Animation animation){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(animation);
            }
        });
    }
    private void handleClickAnimationXML(Button btn, int animId) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivUitLogo.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, animId));
            }
        });
    }
    private Animation initFadeInAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initFadeOutAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initBlinkAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(100);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(5);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initZoomInAnimation() {
        Animation animation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initZoomOutAnimation() {
        Animation animation = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(3000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }
    private Animation initRotateAnimation() {
        Animation animation = new RotateAnimation(
                0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener);
        return animation;
    }

    private Animation initMoveAnimation() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.75f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animation.setDuration(800);
        animation.setInterpolator(new LinearInterpolator());
        animation.setFillAfter(true);
        return animation;
    }

    private Animation initSlideUpAnimation() {
        ScaleAnimation animation = new ScaleAnimation(
                1.0f, 1.0f, // fromXScale, toXScale
                1.0f, 0.0f, // fromYScale, toYScale
                Animation.RELATIVE_TO_SELF, 0.5f, // pivotXType, pivotXValue
                Animation.RELATIVE_TO_SELF, 0.5f  // pivotYType, pivotYValue
        );
        animation.setDuration(500);
        animation.setFillAfter(true);
        return animation;
    }

    private Animation initBounceAnimation() {
        ScaleAnimation animation = new ScaleAnimation(
                1.0f, 1.0f, // fromXScale, toXScale
                0.0f, 1.0f, // fromYScale, toYScale
                Animation.RELATIVE_TO_SELF, 0.5f, // pivotXType, pivotXValue
                Animation.RELATIVE_TO_SELF, 0.5f  // pivotYType, pivotYValue
        );
        animation.setDuration(500);
        animation.setFillAfter(true);
        animation.setInterpolator(new BounceInterpolator());
        return animation;
    }

    private Animation initCombineAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(new LinearInterpolator());

        // Scale Animation
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 3.0f, // fromXScale, toXScale
                1.0f, 3.0f, // fromYScale, toYScale
                Animation.RELATIVE_TO_SELF, 0.5f, // pivotXType, pivotXValue
                Animation.RELATIVE_TO_SELF, 0.5f  // pivotYType, pivotYValue
        );
        scaleAnimation.setDuration(4000);

        // Rotate Animation
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360, // fromDegrees, toDegrees
                Animation.RELATIVE_TO_SELF, 0.5f, // pivotXType, pivotXValue
                Animation.RELATIVE_TO_SELF, 0.5f  // pivotYType, pivotYValue
        );
        rotateAnimation.setDuration(500);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setRepeatMode(Animation.RESTART);

        // Adding animations to the set
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);

        return animationSet;
    }

    private void findViewsByIds() {
        ivUitLogo = (ImageView) findViewById(R.id.iv_uit_logo);
        btnFadeInXml = (Button) findViewById(R.id.btn_fade_in_xml);
        btnFadeInCode = (Button) findViewById(R.id.btn_fade_in_code);
        btnFadeOutXml = (Button) findViewById(R.id.btn_fade_out_xml);
        btnFadeOutCode = (Button) findViewById(R.id.btn_fade_out_code);
        btnBlinkXml = (Button) findViewById(R.id.btn_blink_xml);
        btnBlinkCode = (Button) findViewById(R.id.btn_blink_code);
        btnZoomInXml = (Button) findViewById(R.id.btn_zoom_in_xml);
        btnZoomInCode = (Button) findViewById(R.id.btn_zoom_in_code);
        btnZoomOutXml = (Button) findViewById(R.id.btn_zoom_out_xml);
        btnZoomOutCode = (Button) findViewById(R.id.btn_zoom_out_code);
        btnRotateXml = (Button) findViewById(R.id.btn_rotate_xml);
        btnRotateCode = (Button) findViewById(R.id.btn_rotate_code);
        btnMoveXml = (Button) findViewById(R.id.btn_move_xml);
        btnMoveCode = (Button) findViewById(R.id.btn_move_code);
        btnSlideUpXml = (Button) findViewById(R.id.btn_slide_up_xml);
        btnSlideUpCode = (Button) findViewById(R.id.btn_slide_up_code);
        btnBounceXml = (Button) findViewById(R.id.btn_bounce_xml);
        btnBounceCode = (Button) findViewById(R.id.btn_bounce_code);
        btnCombineXml = (Button) findViewById(R.id.btn_combine_xml);
        btnCombineCode = (Button) findViewById(R.id.btn_combine_code);
    }
    private void initVariables() {
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Stopped",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        };
    }
}