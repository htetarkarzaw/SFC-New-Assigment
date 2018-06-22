package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.datas.models.NewsModel;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.mvp.views.NewsDetailView;

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    public NewsDetailPresenter(NewsDetailView mView) {
        super(mView);
    }

    public void onFinishUISetup(String newsId) {
        NewsVO newsVO = NewsModel.getInstance().getNewsById(newsId);
        mView.displayNewsDetail(newsVO);
    }
}
