package com.wust.mymusic.ui.activities;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wust.mymusic.BaseApp;
import com.wust.mymusic.R;
import com.wust.mymusic.networking.NetEasyMusicService;
import com.wust.mymusic.presenter.TestPresenter;
import com.wust.mymusic.presenter.impl.TestPresenterImpl;
import com.wust.mymusic.util.ToastUtils;
import com.wust.mymusic.view.TestView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class TestActivity extends BaseApp implements TestView{


    @BindView(R.id.btn_startdownload)
    Button btnStartDownload;
    @BindView(R.id.btn_stopdownload)
    Button btnStopDownload;
    @BindView(R.id.save_file_progressBar)
    ProgressBar mSaveFileProgressBar;
    @BindView(R.id.download_file_progressBar)
    ProgressBar mDownloadFileProgressBar;

    private TestPresenter mTestPresenter;

    @Inject
    public NetEasyMusicService mNetEasyMusicService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getDeps().inject(this);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView(){
        setUpMVP();
    }

    private void initData(){

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void setUpMVP() {
        mTestPresenter = new TestPresenterImpl(mNetEasyMusicService);
        mTestPresenter.attachView(this);
    }

    @Override
    public void showProgress() {
        ToastUtils.showShort("开始下载");
    }

    @Override
    public void hideProgress() {
        ToastUtils.showShort("结束下载");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mTestPresenter != null){
            mTestPresenter.detachView();
        }
    }

    @OnClick(R.id.btn_startdownload)
    public void startDownloadCheck(){
        TestActivityPermissionsDispatcher.startDownloadWithPermissionCheck(this);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void startDownload(){
        showProgress();
        mTestPresenter.startDownloadFile("http://192.168.1.10:8080/springbootweb01/file/download?filename=tem.txt");
    }

    @OnClick(R.id.btn_startdownload)
    public void stopDownload(){

    }

    @Override
    public void setDownloadProgress(int progress) {
        mDownloadFileProgressBar.setProgress(progress);
    }

    @Override
    public void setSaveProgress(int progress) {
        mSaveFileProgressBar.setProgress(progress);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        TestActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDenied() {
        Toast.makeText(this, "无法获得权限,无法使用该功能", Toast.LENGTH_SHORT).show();
    }
}
