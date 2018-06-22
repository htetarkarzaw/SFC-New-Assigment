package com.padcmyanmar.sfc.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;
import com.padcmyanmar.sfc.mvp.views.BaseView;
import com.padcmyanmar.sfc.viewholders.BaseViewHolder;
import com.padcmyanmar.sfc.viewholders.NewsViewHolder;
import com.padcmyanmar.sfc.viewholders.SkySecondViewHolder;
import com.padcmyanmar.sfc.viewholders.SkyTopViewHolder;

public class SkyNewsAdapter extends BaseRecyclerAdapter<BaseViewHolder, NewsVO> {

    private  final int TOP_VIEW = 0;
    private final int SECOND_VIEW = 1;

    private NewsItemDelegate mNewsItemDelegate;

    public SkyNewsAdapter(Context context , NewsItemDelegate newsItemDelegate) {
        super(context);
        mNewsItemDelegate = newsItemDelegate;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder bHolder = null;
        if(viewType==TOP_VIEW){
            View v= mLayoutInflator.inflate(R.layout.view_holder_sky_top,parent,false);
            bHolder= new SkyTopViewHolder(v,mNewsItemDelegate);
        }else if(viewType==SECOND_VIEW){
            View v=mLayoutInflator.inflate(R.layout.view_holder_sky_second,parent,false);
            bHolder= new SkySecondViewHolder(v,mNewsItemDelegate);
        }
        return bHolder;
    }

    public int getItemViewType(int position) {
        if (position == 0) {
        return TOP_VIEW;
    }
    return SECOND_VIEW;


    }
}
