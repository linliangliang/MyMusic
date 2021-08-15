package com.wust.mymusic.view;

import java.util.List;

public interface MainView extends BaseView{

    //void updateNavProfile(NavProfile navProfile);

    //更新细节
    //void updateDetail(DetailResponse detailResponse);

    //void updateMusicMenu(List<MusicMenu> musicMenus);

    void updateMiniCoverAndTitle();

    void setUpNavHeader();

    void navigateToLocalView();

    void navigateToRecentPlayView();

    void navigateToDownloadsView();

    void navigateToStationsView();

    void navigateToFavorites();

    //void navigatePlaylist(RecommendSongItem recommendSongItem);

    /**
     * 网络故障
     * @param errorMsg
     */
    void showNetworkError(String errorMsg);

    //void showBanner(List<BannersItem> bannersItems);

    //void showRecommendSongList(List<RecommendSongItem> recommendSongItems);
}
