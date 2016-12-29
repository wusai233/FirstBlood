package sai.org.com.firstboold.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


import sai.org.com.firstboold.R;
import sai.org.com.firstboold.base.GuideBaseFragment;

/**
 * Created by wujamie on 16/12/27.
 */

public class FirstGuildFragment extends GuideBaseFragment {

    private ImageView ivReward;
    private ImageView ivGold;

    private Bitmap goldBitmap;
    private boolean started;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guide_first, null);
        ivReward = (ImageView) rootView.findViewById(R.id.iv_reward);
        ivGold = (ImageView) rootView.findViewById(R.id.iv_gold);

        goldBitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.icon_gold);
        startAnimation();
        return rootView;
    }

    @Override
    public void startAnimation() {
        started = true;
        //向下移动动画 硬币的高度*2+80
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, goldBitmap.getHeight() * 2 + 80);
        translateAnimation.setDuration(500);
        translateAnimation.setFillAfter(true);
        ivGold.startAnimation(translateAnimation);

        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (started) {
                    ivReward.setVisibility(View.VISIBLE);
                    //硬币移动动画结束开启缩放动画
                    Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.reward_launcher);
                    ivReward.startAnimation(anim);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //缩放动画结束 开启改变透明度动画
                            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                            alphaAnimation.setDuration(1000);
                            ivReward.startAnimation(alphaAnimation);
                            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    //透明度动画结束隐藏图片
                                    ivReward.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void stopAnimation() {
        started = false;//结束动画时标示符设置为false
        ivGold.clearAnimation();//清空view上的动画
    }
}
