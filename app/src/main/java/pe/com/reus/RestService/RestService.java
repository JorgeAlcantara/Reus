package pe.com.reus.RestService;

import pe.com.reus.Model.Reniec;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestService {

    //Buscar DNI en la Reniec
    @GET("api/reniecs/{NumeroDNI}")
    Call<Reniec> buscarDni(@Path("NumeroDNI") Long dni);
}
