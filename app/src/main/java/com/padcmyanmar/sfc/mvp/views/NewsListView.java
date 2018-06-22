package com.padcmyanmar.sfc.mvp.views;

import com.padcmyanmar.sfc.datas.vo.NewsVO;

import java.util.List;

public interface NewsListView extends BaseView {

    void displayNewsList(List<NewsVO> newsList);

    void LaunchNewsDetail(String newsId);
}
