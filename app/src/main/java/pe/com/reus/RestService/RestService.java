package pe.com.reus.RestService;

import pe.com.reus.Model.Actor;
import pe.com.reus.Model.Reniec;
import pe.com.reus.Model.Reu;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestService {

    //Buscar DNI en la Reniec
    @GET("api/Reniecs/{NumeroDNI}")
    Call<Reniec> buscarDni(@Path("NumeroDNI") Long dni);

    //Registrar Reus
    @POST("api/Reus")
    Call<Reu> registrarReu(@Body Reu reu);

    @POST("api/Actors")
    Call<Actor> registrarActor(@Body Actor actor);

    @PUT("api/Actors/{IdActor}")
    Call<Actor> actualizarActor(@Path("IdActor") int idActor,@Body Actor actor);
}
