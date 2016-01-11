package com.yydcdut.note.mvp.v.note.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.yydcdut.note.R;
import com.yydcdut.note.mvp.p.note.impl.MapPresenterImpl;
import com.yydcdut.note.mvp.v.BaseActivity;
import com.yydcdut.note.mvp.v.note.IMapView;
import com.yydcdut.note.utils.AppCompat;
import com.yydcdut.note.utils.Const;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yuyidong on 16/1/8.
 */
public class MapActivity extends BaseActivity implements IMapView {
    @Bind(R.id.map)
    MapView mMapView;/* map */

    @Inject
    MapPresenterImpl mMapPresenter;

    @Override
    public boolean setStatusBar() {
        return true;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_map;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
        Bundle bundle = getIntent().getExtras();
        mMapPresenter.bindData(bundle.getInt(Const.CATEGORY_ID_4_PHOTNOTES), bundle.getInt(Const.PHOTO_POSITION),
                bundle.getInt(Const.COMPARATOR_FACTORY));
        mIPresenter = mMapPresenter;
    }

    @Override
    public void initUiAndListener() {
        ButterKnife.bind(this);
        mMapPresenter.attachView(this);
        initToolBarUI();
        mMapView.showZoomControls(false);//隐藏缩放控件
    }

    private void initToolBarUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        AppCompat.setElevation(toolbar, getResources().getDimension(R.dimen.ui_elevation));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(R.anim.activity_no_animation, R.anim.activity_no_animation);
                setResult(RESULT_NOTHING, null);
                finish();
                overridePendingTransition(R.anim.activity_no_animation, R.anim.activity_no_animation);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public BaiduMap getBaiduMap() {
        return mMapView.getMap();
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.activity_no_animation, R.anim.activity_no_animation);
        setResult(RESULT_NOTHING, null);
        finish();
        overridePendingTransition(R.anim.activity_no_animation, R.anim.activity_no_animation);
    }
}
