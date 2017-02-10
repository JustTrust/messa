package com.belichenko.a.messa.ui.mvp.mvp_viev;

import com.belichenko.a.messa.data.model.Ribot;

import java.util.List;

import com.belichenko.a.messa.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
