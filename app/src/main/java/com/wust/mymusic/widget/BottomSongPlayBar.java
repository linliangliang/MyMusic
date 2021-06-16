package com.wust.mymusic.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wust.mymusic.R;
import com.wust.mymusic.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 界面底部的歌曲遥控器
 * 用EventBus来控制它
 */
public class BottomSongPlayBar extends RelativeLayout {
    private static final String TAG = "BottomSongPlayBar";

    private Context mContext;

    private RelativeLayout rlSongController;
    private CircleImageView ivCover;
    private ImageView ivPlay, ivController;
    private TextView tvSongName, tvSongSinger;
    private LinearLayout llSongInfo;

/*
    private SongInfo currentSongInfo;
*/

/*    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayMusicEvent(MusicStartEvent event) {
        LogUtil.d(TAG, "MusicStartEvent :" + event);
        setSongBean(event.getSongInfo());
        ivPlay.setImageResource(R.drawable.shape_stop);
    }*/

   /* @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStopMusicEvent(StopMusicEvent event) {
        LogUtil.d(TAG, "onStopMusicEvent");
        setSongBean(event.getSongInfo());
        ivPlay.setImageResource(R.drawable.shape_play);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPauseMusicEvent(MusicPauseEvent event) {
        LogUtil.d(TAG, "onPauseMusicEvent");
        ivPlay.setImageResource(R.drawable.shape_play);
    }*/

    public BottomSongPlayBar(Context context) {
        this(context, null);
    }

    public BottomSongPlayBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomSongPlayBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*EventBus.getDefault().register(this);*/
        mContext = context;
        initView();
        /*initListener();
        initSongInfo();*/
    }

    private void initView() {
        /*rlSongController = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_bottom_songplay_control, this, true);
        ivCover = rlSongController.findViewById(R.id.iv_cover);
        ivPlay = rlSongController.findViewById(R.id.iv_bottom_play);
        ivController = rlSongController.findViewById(R.id.iv_bottom_controller);
        tvSongName = rlSongController.findViewById(R.id.tv_songname);
        tvSongSinger = rlSongController.findViewById(R.id.tv_singer);
        llSongInfo = rlSongController.findViewById(R.id.ll_songinfo);*/
    }
}