package com.example.hrms.view;

import java.util.List;

public interface Iview {
    void setAdapterbyQuery(List list) throws ClassNotFoundException;

    void setDataSuccess(Boolean isSuccess);
}
