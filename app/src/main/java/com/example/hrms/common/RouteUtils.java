package com.example.hrms.common;

import android.content.Context;
import android.content.Intent;

import com.example.hrms.main.view.dialog.AlertDialogDepartmentActivity;
import com.example.hrms.main.view.dialog.AlertDialogEmployeeActivity;

public class RouteUtils {
    public static final int INSERT = 0;
    public static final int UPDATE = 1;
    public static final int DELETE = 2;
    public static final int RETRIEVE = 3;

    public static void gotoActivity(Context activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    public static void gotoInsertEmployeeActivity(Context activity) {
        Intent intent = new Intent(activity, AlertDialogEmployeeActivity.class);
        intent.putExtra("tag", INSERT);
        activity.startActivity(intent);
    }

    public static void gotoUpdateEmployeeActivity(Context activity) {
        Intent intent = new Intent(activity, AlertDialogEmployeeActivity.class);
        intent.putExtra("tag", UPDATE);
        activity.startActivity(intent);
    }

    public static void gotoDeleteEmployeeActivity(Context activity) {
        Intent intent = new Intent(activity, AlertDialogEmployeeActivity.class);
        intent.putExtra("tag", DELETE);
        activity.startActivity(intent);
    }
    public static void gotoInsertDepartmentActivity(Context activity) {
        Intent intent = new Intent(activity, AlertDialogDepartmentActivity.class);
        intent.putExtra("tag", INSERT);
        activity.startActivity(intent);
    }

    public static void gotoUpdateDepartmentActivity(Context activity) {
        Intent intent = new Intent(activity, AlertDialogDepartmentActivity.class);
        intent.putExtra("tag", UPDATE);
        activity.startActivity(intent);
    }

    public static void gotoDeleteDepartmentActivity(Context activity) {
        Intent intent = new Intent(activity, AlertDialogDepartmentActivity.class);
        intent.putExtra("tag", DELETE);
        activity.startActivity(intent);
    }
}