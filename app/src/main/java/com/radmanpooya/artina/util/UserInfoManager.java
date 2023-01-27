package com.radmanpooya.artina.util;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoManager {
    private SharedPreferences sharedPreferences;

    public UserInfoManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_id", userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString("user_id", "");
    }

}
