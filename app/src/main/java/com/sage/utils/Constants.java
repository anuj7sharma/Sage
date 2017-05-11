package com.sage.utils;

/**
 * Created by Mobilyte India Pvt Ltd on 2/28/2017.
 */

public interface Constants {
    // Environment to use when creating an instance of Wallet.WalletOptions

    /*
    API BASE URL
     */
    String DEV_APIURL ="http://10.20.1.176:8000/";
    String LIVE_APIURL ="http://starlord.hackerearth.com/";

    String API_URL = DEV_APIURL;

    String DEF_PROFILE_URL = "https://organicthemes.com/demo/profile/files/2012/12/profile_img.png";

    String LOGIN_API = "polls/login";
    String REGISTER_API = "polls/register";
    String GET_INTEREST_API = "polls/get_interests";
    String SAVE_INTEREST_API = "polls/save_interests";
    String GET_TIMELINE = "polls/get_timeline";
    String ADD_POST = "polls/add_post";


    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}
