package pe.com.reus.RestService;

import java.util.List;

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

    //Validar usuario y password de Usuario
    @GET("api/actor/{nombre}/{password}")
    Call<Actor> loguear(@Path("nombre") String nombre, @Path("password") String password);

    //Actualizar Actor
    @PUT("api/actor/{idactor}")
    Call<Actor> actualizarActor(@Path("idactor") int idActor,@Body Actor actor);

    @POST("api/registrarActor")
    Call<Actor> registrarActor(@Body Actor actor);

    //Registrar Reus
    @POST("api/registrarReu")
    Call<Reu> registrarReu(@Body Reu reu);

    //Listar Reus
    @GET("api/reus")
    Call<List<Reu>> listarReu();




}
