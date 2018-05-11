package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.Film;
import domain.services.ActorServices;
import domain.services.FilmServices;

@Path("/actors")
public class ActorsResources {
	private ActorServices db = new ActorServices();
	private FilmServices dbFilm = new FilmServices();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAll(){
		return db.getAll();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Actor actor) {
		db.add(actor);
		return Response.ok(actor.getId()).build();
	}		
	@GET
	@Path("/{actorId}/films")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getFilms(@PathParam("actorId") int actorId){
		Actor result = db.get(actorId);
		if(result==null) {
			return null;
		}
		if(result.getFilms()==null) {
			result.setFilms(new ArrayList<Film>());
		}
		return result.getFilms();			
	}
	@POST
	@Path("/{id}/films")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFilm(@PathParam("id") int actorId, Film film) {
		Actor result = db.get(actorId);
		if(result==null) {
			return Response.status(404).build();
		}
		if(result.getFilms()==null) {
			result.setFilms(new ArrayList<Film>());
		}
		result.getFilms().add(film);
		return Response.ok().build();
	}
	@POST
	@Path("/{idActor}/films/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFilm(@PathParam("idActor") int actorId, @PathParam("id") int id) {
		Actor result = db.get(actorId);
		Film film = dbFilm.get(id);
		if(result==null || film==null) {
			return Response.status(404).build();
		}
		if(result.getFilms()==null) {
			result.setFilms(new ArrayList<Film>());
		}
		result.getFilms().add(film);
		return Response.ok().build();
	}
}
