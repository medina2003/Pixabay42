package kg.geektech.pixabay42;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import kg.geektech.pixabay42.databinding.ActivityMainBinding;
import kg.geektech.pixabay42.network.model.Adapter;
import kg.geektech.pixabay42.network.model.RetrofitService;
import kg.geektech.pixabay42.network.model.model.Hit;
import kg.geektech.pixabay42.network.model.model.PixabayModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    RetrofitService retrofitService;
    Adapter adapter;

    public static final String KEY = "27641752-6b1c0d13581ca0b3b42251e9c";
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitService = new RetrofitService();
        initClickers();

        binding.swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count++;
                String word = binding.wordEd.getText().toString();
                getImageFromApi(word, count, 10);
                binding.swipeLayout.setRefreshing(false);
            }
        });
    }

    private void initClickers() {
        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = binding.wordEd.getText().toString();
                getImageFromApi(word, count, 10);
                count = 1;
            }
        });
        binding.applyBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                String word = binding.wordEd.getText().toString();
                getImageFromApi(word, count, 10);
            }
        });
    }

    private void getImageFromApi(String word, int page, int perPage) {
        retrofitService.getApi().getImages(KEY,word,page,perPage).enqueue(new Callback<PixabayModel>() {
            @Override
            public void onResponse(Call<PixabayModel> call, Response<PixabayModel> response) {
                if (response.isSuccessful()){
                    adapter = new Adapter((ArrayList<Hit>) response.body().getHits());
                    binding.recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<PixabayModel> call, Throwable t) {
                Log.e("ololo","onFailure: " + t.getLocalizedMessage());
            }
        });



    }
}


