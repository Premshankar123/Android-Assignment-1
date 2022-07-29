package chalkup.sekhontech.androidassignment_1.viewmodels;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import chalkup.sekhontech.androidassignment_1.apis.APIServices;
import chalkup.sekhontech.androidassignment_1.apis.RetroInstance;
import chalkup.sekhontech.androidassignment_1.models.ProductModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductViewModel extends ViewModel
{
    private MutableLiveData<List<ProductModel>> movielist;

    public ProductViewModel(){
        movielist=new MutableLiveData<>();
    }

    public MutableLiveData<List<ProductModel>> getMovielistObserver()
    {
        return movielist;
    }

    public void makeApiCall()
    {
        APIServices apiServices= RetroInstance.getRetroClient().create(APIServices.class);
        Call<List<ProductModel>> call=apiServices.getMovieList();
        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
               movielist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
            movielist.postValue(null);
            Log.e("Error :",t.getMessage().toString());
            }
        });


    }
}
