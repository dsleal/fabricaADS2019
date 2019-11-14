package contrato.com.boostrap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient_bkp {

    //public static final String ENDPOINT = "https://jsonplaceholder.typicode.com/";
    public static final String ENDPOINT = "http://192.168.137.1:8080/api/";

    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}

