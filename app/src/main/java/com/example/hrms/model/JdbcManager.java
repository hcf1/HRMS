package com.example.hrms.model;

import com.example.hrms.entity.DefaultEntity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import java.sql.*;

import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class JdbcManager {
    public static Observable<ResultSet> query(String querySting) {
        return Observable.just(querySting)
                .observeOn(Schedulers.io())
                .map(new Func1<String, ResultSet>() {
                    @Override
                    public ResultSet call(String s) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:mysql://180.76.142.171:3306/HRMS?characterEncoding=utf-8", "root", "sigechoupijiang");
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(s);
                                return resultSet;
                            } catch (SQLException e) {
                                e.printStackTrace();
                                return null;
                            }

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
    }

    public static Observable<DefaultEntity> insert(String querySting) {
        return Observable.just(querySting)
                .observeOn(Schedulers.io())
                .map(new Func1<String, DefaultEntity>() {
                    @Override
                    public DefaultEntity call(String s) {
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            try {
                                Connection connection = DriverManager.getConnection("jdbc:mysql://180.76.142.171:3306/HRMS?characterEncoding=utf-8", "root", "sigechoupijiang");
                                Statement statement = connection.createStatement();
                                DefaultEntity defaultEntity = new DefaultEntity();
                                defaultEntity.affectedRows = statement.executeUpdate(s);
                                connection.close();
                                return defaultEntity;
                            } catch (SQLException e) {
                                e.printStackTrace();
                                return null;
                            }

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                });
    }
}
