package com.padcmyanmar.sfc.datas.database;

import android.arch.persistence.room.TypeConverter;

public class AttractionImagesTypeConverter {

    @TypeConverter
    public static String[] toStringArray(String images) {
        return images.split(",");
    }

    @TypeConverter
    public static String toString(String[] images) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String image : images) {
            stringBuilder.append(image).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
