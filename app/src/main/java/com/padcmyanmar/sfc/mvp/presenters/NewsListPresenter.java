package com.padcmyanmar.sfc.mvp.presenters;

import com.padcmyanmar.sfc.datas.models.NewsModel;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.mvp.views.NewsListView;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class NewsListPresenter extends BasePresenter<NewsListView> implements NewsItemDelegate {

    public NewsListPresenter(NewsListView mView) {
        super(mView);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startNewsLoading();
    }

    private void startNewsLoading() {
        PublishSubject<GetNewsResponse> newsPSubject = PublishSubject.create();
        NewsModel.getInstance().loadNewsSubject(newsPSubject);
        newsPSubject.subscribe(new Observer<GetNewsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetNewsResponse getNewsResponse) {
                if (getNewsResponse != null)
                    if (getNewsResponse.getNewsList() != null && getNewsResponse.getNewsList().size() > 0)
                        mView.displayNewsList(getNewsResponse.getNewsList());
//                mNewsAdapter.setNewData(getNewsResponse.getNewsList());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onTapComment() {

    }

    @Override
    public void onTapSendTo() {

    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapStatistics() {

    }

    @Override
    public void onTapNews(NewsVO vo) {
        mView.LaunchNewsDetail(vo.getNewsId());
    }
}
