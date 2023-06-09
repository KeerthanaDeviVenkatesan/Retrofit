package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")

    Call<List<Post>> getPosts(@Query("userId") int userId,@Query("_sort") String sort,@Query("_order") String order);

    @GET("posts/{id}/comments")

    Call<List<Comments>> getComments(@Path("id") int id);

    @POST("posts")
     Call<Post> createPost(@Body Post post);


}
