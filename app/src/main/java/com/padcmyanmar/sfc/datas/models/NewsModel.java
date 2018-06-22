package com.padcmyanmar.sfc.datas.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.padcmyanmar.sfc.datas.database.AppDataBase;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.datas.vo.PublicationVO;
import com.padcmyanmar.sfc.events.RestApiEvents;
import com.padcmyanmar.sfc.network.MMNewsAPI;
import com.padcmyanmar.sfc.network.MMNewsDataAgentImpl;
import com.padcmyanmar.sfc.network.reponses.GetNewsResponse;
import com.padcmyanmar.sfc.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aung on 12/3/17.
 */

public class NewsModel {

    private static NewsModel objInstance;

    private List<NewsVO> mNews;
    private int mmNewsPageIndex = 1;

     AppDataBase mAppDatabase;


    private Map<String, NewsVO> newsVOMap;

     //RxJava
    private MMNewsAPI mmNewsAPI;

    private NewsModel(Context context) {
        mAppDatabase = AppDataBase.getNewsDatabase(context);

        EventBus.getDefault().register(this);
        mNews = new ArrayList<>();
        newsVOMap = new HashMap<>();
//        startLoadingMMNews();
        initMMnewAPI();
    }

    public static NewsModel getInstance() {
        if(objInstance != null) {
            return objInstance;
        }
        throw new RuntimeException("NewsModel shouldn't be null at this point.");
    }

    public static void initDatabase(Context context) {
        objInstance = new NewsModel(context);
    }

    public void startLoadingMMNews() {
        MMNewsDataAgentImpl.getInstance().loadMMNews(AppConstants.ACCESS_TOKEN, mmNewsPageIndex);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsDataLoaded(RestApiEvents.NewsDataLoadedEvent event) {
        mNews.addAll(event.getLoadNews());
        for (NewsVO newsVO : event.getLoadNews()) {
            NewsVO vo = newsVO;
            PublicationVO pVo = vo.getPublication();
            vo.setPublicationId(pVo.getPublicationId());
            long pub = mAppDatabase.publicationDao().insertPublication(pVo);
            long news = mAppDatabase.newsDao().insertNew(vo);
            Log.e("Success", news+"onNewsDataLoaded: "+pub );
        }

        mmNewsPageIndex = event.getLoadedPageIndex() + 1;
    }

    //Rxjava

    private void initMMnewAPI(){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        mmNewsAPI = retrofit.create(MMNewsAPI.class);
    }

    public void loadNewsSubject(final PublishSubject<GetNewsResponse> pSubject){
        Observable<GetNewsResponse> newsObserable = mmNewsAPI.loadMMNewsRx(mmNewsPageIndex,AppConstants.ACCESS_TOKEN);
        newsObserable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetNewsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GetNewsResponse getNewsResponse) {
                        for (NewsVO newsVO : getNewsResponse.getNewsList()) {
                            newsVOMap.put(newsVO.getNewsId(), newsVO);
                        }
                        pSubject.onNext(getNewsResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public NewsVO getNewsById(String newsId) {
        return newsVOMap.get(newsId);
    }

}
