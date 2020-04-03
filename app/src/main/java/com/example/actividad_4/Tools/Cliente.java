package com.example.actividad_4.Tools;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Cliente {
public static final String BASE_URL = "http://192.168.1.98:8000/";

public static final String GET_ALL = "api/v1/user/user_list/";

public static final String POST_ALL = "api/v1/user/user_list/";

public static final String PUT_ALL = "api/v1/user/user_detail/";

public static final String GET_ID = "api/v1/user/user_detail/";

public static final String LOGIN = "api/v1/login/";

public static  AsyncHttpClient client = new AsyncHttpClient();

public static  void getAllClient(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
    client.get(BASE_URL+GET_ALL,asyncHttpResponseHandler);
};
public static  void getByidClient(String id,RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
    client.get(BASE_URL+GET_ID+id+"/",asyncHttpResponseHandler);
};
public static  void postLogin(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
    client.post(BASE_URL+LOGIN,params,asyncHttpResponseHandler);
};
public static  void postClient(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
    client.post(BASE_URL+POST_ALL,params,asyncHttpResponseHandler);
};
public static  void putClient(String id,RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
    client.put(BASE_URL+PUT_ALL+id+"/",params,asyncHttpResponseHandler);
};
public static  void deleteClient(String id, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.delete(BASE_URL+PUT_ALL+id+"/",asyncHttpResponseHandler);
};

}
