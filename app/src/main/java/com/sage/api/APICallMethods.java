/*
 *
 *
 *  * Copyright © 2016, Mobilyte Inc. and/or its affiliates. All rights reserved.
 *  *
 *  * Redistribution and use in source and binary forms, with or without
 *  * modification, are permitted provided that the following conditions are met:
 *  *
 *  * - Redistributions of source code must retain the above copyright
 *  *    notice, this list of conditions and the following disclaimer.
 *  *
 *  * - Redistributions in binary form must reproduce the above copyright
 *  * notice, this list of conditions and the following disclaimer in the
 *  * documentation and/or other materials provided with the distribution.
 *
 * /
 */
package com.sage.api;


import com.sage.bean.GetInterestsResponse;
import com.sage.bean.LoginResponse;
import com.sage.utils.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Mobilyte on 2/17/2016.
 */
public interface APICallMethods {

    @FormUrlEncoded
    @POST(Constants.LOGIN_API)
    Call<LoginResponse> checkLogin(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(Constants.REGISTER_API)
    Call<LoginResponse> register(@FieldMap Map<String, String> options);

    @GET(Constants.GET_INTEREST_API)
    Call<GetInterestsResponse> getInterests();

    @GET(Constants.GET_TIMELINE)
    Call<GetInterestsResponse> getTimeline();
    /*
    @FormUrlEncoded
    @POST(ConstantFile.CHECK_IS_DRIVER)
    Call<CheckIsDriverResponse> checkIsDriver(@FieldMap Map<String, String> options);

    @Multipart
    @POST(ConstantFile.BECOME_A_DRIVER)
    Call<BasicResponse>becomeDriver(@PartMap Map<String, RequestBody> options, @Part MultipartBody.Part profile_pic
            , @Part MultipartBody.Part license_pic, @Part MultipartBody.Part insurance_pic);*/

}
