package com.example.hrms.model;

import com.example.hrms.entity.DefaultEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class JdbcManager {
    public static Connection conn = null;
    public static Statement statement = null;

    public static void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (conn == null) {
                conn = DriverManager.getConnection("jdbc:mysql://202.182.118.120:3306/hr?characterEncoding=utf-8", "root", "123456");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                if (statement == null) {
                    statement = conn.createStatement();
                }
            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Observable<ResultSet> query(String querySting) {
        return Observable.just(querySting)
                .observeOn(Schedulers.io())
                .map(new Func1<String, ResultSet>() {
                    @Override
                    public ResultSet call(String s) {
                        init();
                        ResultSet resultSet = null;
                        try {
                            resultSet = statement.executeQuery(s);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return resultSet;

                    }

                });
    }

    public static Observable<DefaultEntity> executeUpdate(String querySting) {
        return Observable.just(querySting)
                .observeOn(Schedulers.io())
                .map(new Func1<String, DefaultEntity>() {
                    @Override
                    public DefaultEntity call(String s) {
                        init();
                        DefaultEntity defaultEntity = new DefaultEntity();
                        try {
                            defaultEntity.affectedRows = statement.executeUpdate(s);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return defaultEntity;

                    }
                });
    }

    public static void onDestroy() throws SQLException {
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
