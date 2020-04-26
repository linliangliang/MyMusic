package com.wust.mymusic.presenter;

import com.wust.mymusic.model.entities.User;

public interface LoginPresenter {
    void validateCredentials(User user);
}
