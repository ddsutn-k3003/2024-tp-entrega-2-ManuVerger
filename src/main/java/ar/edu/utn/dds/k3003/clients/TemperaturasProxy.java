package ar.edu.utn.dds.k3003.clients;

import ar.edu.utn.dds.k3003.facades.dtos.TemperaturaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

public class TemperaturasProxy {

    private final TemperaturaRetrofitClient service;

    public TemperaturasProxy(ObjectMapper objectMapper, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();

        this.service = retrofit.create(TemperaturaRetrofitClient.class);
    }

    public List<TemperaturaDTO> obtenerTemperaturasPorHeladeraId(int heladeraId) throws IOException {
        return service.obtenerTemperaturasPorHeladeraId(heladeraId).execute().body();
    }

    private interface TemperaturaRetrofitClient {
        @GET("/temperaturas/{heladeraId}")
        retrofit2.Call<List<TemperaturaDTO>> obtenerTemperaturasPorHeladeraId(@Path("heladeraId") int heladeraId);
    }
}
