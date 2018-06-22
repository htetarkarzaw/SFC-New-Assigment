package com.padcmyanmar.sfc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.NewsAdapter;
import com.padcmyanmar.sfc.adapters.SkyNewsAdapter;
import com.padcmyanmar.sfc.components.EmptyViewPod;
import com.padcmyanmar.sfc.components.SmartRecyclerView;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.presenters.NewsListPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkyNewsActivity extends BaseActivity implements NewsListView {

    @BindView(R.id.skyRcNews)
    SmartRecyclerView skyRcViews;

    @BindView(R.id.vp_empty_news)
    EmptyViewPod vpEmptyNews;

    private SkyNewsAdapter skyNewsAdapter;
    NewsListPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sky_news);
        ButterKnife.bind(this,this);

        mPresenter = new NewsListPresenter(this);
        mPresenter.onCreate();

        skyRcViews.setEmptyView(vpEmptyNews);
        skyRcViews.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        skyNewsAdapter = new SkyNewsAdapter(getApplicationContext(), mPresenter);
        skyRcViews.setAdapter(skyNewsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }

    @Override
    public void displayNewsList(List<NewsVO> newsList) {
        skyNewsAdapter.appendNewData(newsList);
    }

    @Override
    public void LaunchNewsDetail(String newsId) {
        Intent intent = NewsDetailsActivity.newIntent(getApplicationContext(), newsId);
        startActivity(intent);
    }

    @Override
    public void displayErrorMsg(String errMessage) {
        Snackbar.make(skyRcViews, errMessage, Snackbar.LENGTH_INDEFINITE).show();
    }
}
