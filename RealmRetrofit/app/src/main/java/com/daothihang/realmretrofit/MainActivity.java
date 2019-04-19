package com.daothihang.realmretrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.daothihang.realmretrofit.adapters.AdapterListUser;
import com.daothihang.realmretrofit.model.User;
import com.daothihang.realmretrofit.model.Users;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    private Realm realm;
    Context mContext;
    private ArrayList<User> userArrayList;
    private RecyclerView recyclerView;
    private AdapterListUser eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userArrayList = new ArrayList<>();
        loadData();
        addControl();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void addControl() {
        userArrayList = new ArrayList<>();
        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(eLayoutManager);
        eAdapter = new AdapterListUser(userArrayList, mContext);
        recyclerView.setAdapter(eAdapter);
    }

    private void loadData() {
        recyclerView = findViewById(R.id.rv_user);
        userArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiService = retrofit.create(ApiInterface.class);
        Call<List<User>> call = apiService.getAll();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, final Response<List<User>> response) {
                if (response.body().size() != 0) {
                    userArrayList.addAll(response.body());
                    userArrayList.size();
                    eAdapter.notifyDataSetChanged();

                    Realm.init(MainActivity.this);

                    realm = Realm.getDefaultInstance();
                    for (int i = 0; i < response.body().size(); i++) {
                        final int id=response.body().get(i).getId();
                        final String login=response.body().get(i).getLogin();
                        final String avatar=response.body().get(i).getAvatar_url();
                        final String url=response.body().get(i).getUrl();
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm bgRealm) {

                                Users users = new Users();
                                users.setId(id);
                                users.setLogin(login);
                                users.setAvatar_url(avatar);
                                users.setUrl(url);
                                bgRealm.insert(users);
                                bgRealm.commitTransaction();
                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess() {
                               
                            }
                        });
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Sai", "onFailure: " + t.getMessage());
            }
        });

    }
}
