package com.padcmyanmar.sfc.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.adapters.NewsImagesPagerAdapter;
import com.padcmyanmar.sfc.datas.models.NewsModel;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.presenters.NewsDetailPresenter;
import com.padcmyanmar.sfc.mvp.views.NewsDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aung on 11/11/17.
 */

public class NewsDetailsActivity extends BaseActivity implements NewsDetailView{

    private static final String IE_NEWS_ID = "IE_NEWS_ID";
    private String mNewsId;


    @BindView(R.id.vp_news_details_images)
    ViewPager vpNewsDetailsImages;

    @BindView(R.id.iv_publication_logo)
    ImageView ivPublicationLogo;

    @BindView(R.id.tv_publication_name)
    TextView tvPublicationName;

    @BindView(R.id.tv_published_date)
    TextView tvPublishedDate;

    @BindView(R.id.tv_news_details)
    TextView tvNewsDetails;

    private NewsModel newsModel;

    NewsDetailPresenter mPresenter;

    public static Intent newIntent(Context context, String newsId) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(IE_NEWS_ID, newsId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this, this);
        mPresenter = new NewsDetailPresenter(this);
        mPresenter.onCreate();

        mNewsId = getIntent().getStringExtra(IE_NEWS_ID);

//        newsModel  = ViewModelProviders.of(this).get(NewsModel.class);
        newsModel.initDatabase(this);
//        NewsVO mNew = newsModel.getNew(mNewsId);

//        tvNewsDetails.setText(mNew.getDetails());
//        tvPublishedDate.setText(mNew.getPostedDate());

        NewsImagesPagerAdapter newsImagesPagerAdapter = new NewsImagesPagerAdapter(getApplicationContext());
        vpNewsDetailsImages.setAdapter(newsImagesPagerAdapter);
        mPresenter.onFinishUISetup(mNewsId);
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
    public void displayNewsDetail(NewsVO newsVO) {

    }

    @Override
    public void displayErrorMsg(String errMessage) {

    }
}
