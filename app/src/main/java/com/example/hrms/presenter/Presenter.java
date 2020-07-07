package com.example.hrms.presenter;

import com.example.hrms.entity.DefaultEntity;
import com.example.hrms.entity.EmployeeEntity;
import com.example.hrms.model.JdbcManager;
import com.example.hrms.view.Iview;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class Presenter {
    private Iview iview = null;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public Presenter(Iview iview) {
        this.iview = iview;
    }

    public <T> void getAdapterbyQuery(final T t, String query) throws ClassNotFoundException {
        final List<T> list = new ArrayList<>();
        Subscription subscription = JdbcManager.query(query)
                .observeOn(AndroidSchedulers.mainThread())//在主线程中操作UI
                .doOnNext(new Action1<ResultSet>() {
                    @Override
                    public void call(ResultSet resultSet) {
                        while (true) {
                            try {
                                if (!resultSet.next()) {
                                    break;
                                }
                                if (t.getClass().getName().equals(EmployeeEntity.class.getName())) {
                                    EmployeeEntity employeeEntity = new EmployeeEntity();
                                    employeeEntity.name = resultSet.getString(2);
                                    employeeEntity.gender = resultSet.getString(3);
                                    employeeEntity.age = resultSet.getInt(4);
                                    employeeEntity.addr = resultSet.getString(5);
                                    list.add((T) employeeEntity);
                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        }
                        try {
                            iview.setAdapterbyQuery(list);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }).subscribe();
        compositeSubscription.add(subscription);
    }

    public void insertInToDataBase(String query) {
        Subscription subscription=JdbcManager.insert(query)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<DefaultEntity>() {
                    @Override
                    public void call(DefaultEntity defaultEntity) {
                        if (defaultEntity.affectedRows > 0) {
                            iview.setDataSuccess(true);
                        }
                    }
                }).subscribe();
        compositeSubscription.add(subscription);
    }
    public void onDestroy() {
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }
}
