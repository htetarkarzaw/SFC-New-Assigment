package com.padcmyanmar.sfc.datas.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.padcmyanmar.sfc.datas.vo.ActedUserVO;
import com.padcmyanmar.sfc.datas.vo.CommentActionVO;
import com.padcmyanmar.sfc.datas.vo.FavoriteActionVO;
import com.padcmyanmar.sfc.datas.vo.NewsInImagesVO;
import com.padcmyanmar.sfc.datas.vo.NewsVO;
import com.padcmyanmar.sfc.datas.vo.PublicationVO;
import com.padcmyanmar.sfc.datas.vo.SentToVO;

@Database(entities = {NewsVO.class,
        ActedUserVO.class,
        CommentActionVO.class,
        NewsInImagesVO.class,
        FavoriteActionVO.class,
        PublicationVO.class,
        SentToVO.class} , version = 3,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DB_NAME = "SFC_News_Db_01";

    private static AppDataBase INSTANCE;


    public abstract NewsDao newsDao();

    public abstract NewsInImagesDao newsInImageDao();

//    public abstract PublicationDao publicationDao();

    public abstract PublicationDao publicationDao();

    public abstract ActedUserDao actedUserDao();

    public abstract CommentActionDao commentActionDao();

    public abstract FavroiteActionDao favoriteActionDao();

    public abstract SentToDao sendToDao();

    public static AppDataBase getNewsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context, AppDataBase.class, DB_NAME)
                    .allowMainThreadQueries()   //Remove this after testing. Access to DB should always be from background thread.
//                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destoryInstance() {
        INSTANCE = null;
    }
}
