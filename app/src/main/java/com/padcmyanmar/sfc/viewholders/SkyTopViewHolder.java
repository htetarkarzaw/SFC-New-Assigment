package com.padcmyanmar.sfc.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.sfc.R;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.delegates.NewsItemDelegate;

import butterknife.BindView;
import butterknife.OnClick;

public class SkyTopViewHolder extends BaseViewHolder<NewsVO> {

    @BindView(R.id.tv_published_date)
    TextView tvPublishedDate;

    @BindView(R.id.tv_brief_news)
    TextView tvBriefNews;

    @BindView(R.id.iv_news)
    ImageView ivNewImage;
    NewsVO mData;
    private NewsItemDelegate mDelegate;
    public SkyTopViewHolder(View itemView , NewsItemDelegate newsItemDelegate) {
        super(itemView);
        mDelegate = newsItemDelegate;
    }

    @Override
    public void setData(NewsVO data) {
        mData = data;
        tvPublishedDate.setText(data.getPostedDate());
        tvBriefNews.setText(data.getBrief());
        if(!data.getImages().isEmpty()) {
            Glide.with(ivNewImage.getContext())
                    .load(data.getImages().get(0))
                    .into(ivNewImage);
        }
    }

    @Override
    public void onClick(View view) {
        mDelegate.onTapNews(mData);
    }

}
