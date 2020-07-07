package com.example.hrms.common;

import android.content.Context;
import android.content.Intent;

public class RouteUtils {
    public static void gotoActivity(Context activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }
}
