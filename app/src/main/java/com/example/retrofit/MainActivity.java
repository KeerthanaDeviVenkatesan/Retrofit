package com.example.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.text_result);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

       jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);

//       getPosts();
//       getComments();
        createPost();

    }
    private void getPosts(){
        Call<List<Post>> call=jsonPlaceHolderApi.getPosts(4,"id","desc");
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts=response.body();
                for (Post post:posts){
                    String content="";
                    content +="ID: "+post.getId() +"\n";
                    content +="User ID: "+post.getUserId()+"\n";
                    content +="Title: "+post.getTitle()+"\n";
                    content +="Text: "+post.getText()+"\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }
    private void getComments(){
        Call<List<Comments>> call= jsonPlaceHolderApi.getComments(4);
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()){
                    textView.setText("Code: "+response.code());
                    return;
                }
                List<Comments> comments=response.body();
             for (Comments comments1:comments){
                 String content="";
                 content +="Id : "+comments1.getId()+"\n";
                 content +="Post Id : "+comments1.getPostId() +"\n";
                 content +="Name : "+comments1.getName()+"\n";
                 content +="Email : "+comments1.getEmail()+"\n";
                 content +="Body :"+comments1.getBody()+"\n";

                 textView.append(content);
             }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
    private void createPost(){
        Post post=new Post(23,2,"New Title","New Title");

        Call<Post> call=jsonPlaceHolderApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }
                Post postResponse=response.body();
                String content="";
                content +="Code :"+response.code()+"\n";
                content +="ID: "+postResponse.getId() +"\n";
                content +="User ID: "+postResponse.getUserId()+"\n";
                content +="Title: "+postResponse.getTitle()+"\n";
                content +="Text: "+postResponse.getText()+"\n";

                textView.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }
}