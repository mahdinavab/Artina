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

    public void setAccessToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("access_token", token);
        editor.apply();
    }

    public void setRefreshToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("refresh_token", token);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString("user_id", "");
    }

    public String getAccessToken() {
        return sharedPreferences.getString("access_token", "");
    }

    public String getRefreshToken() {
        return sharedPreferences.getString("refresh_token", "");
    }

}
