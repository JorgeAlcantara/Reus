package pe.com.reus.RestService;

import pe.com.reus.Model.Reniec;
import pe.com.reus.Model.Reu;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestService {

    //Buscar DNI en la Reniec
    @GET("api/reniecs/{NumeroDNI}")
    Call<Reniec> buscarDni(@Path("NumeroDNI") Long dni);

    @POST("api/Eventos")
    Call<Reu> Eventos(@Body Reu eventos);
}
