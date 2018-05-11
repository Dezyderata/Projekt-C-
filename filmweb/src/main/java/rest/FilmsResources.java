package rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.json.JsonObject;
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



@Path("/films")
public class FilmsResources {
	private FilmServices db = new FilmServices();
	private ActorServices dbActor = new ActorServices();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getAll(){
		return db.getAll();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Film film) {
		db.add(film);
		return Response.ok(film.getId()).build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Film result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		return Response.ok(result.getTitle()).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Film f) {
		Film result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		f.setId(id);
		db.update(f);
		return Response.ok().build();
	}
	@PUT
	@Path("/{id}/{note}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateNote(@PathParam("id") int id, @PathParam("note") int note) {
		Film result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		db.updateNote(result, note);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Film result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		db.delete(result);
		return Response.ok().build();
	}
	@GET
	@Path("/{filmId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap<Integer, String> getComments(@PathParam("filmId") int filmId){
		Film result = db.get(filmId);
		if(result==null) {
			return null;
		}
		if(result.getComments()==null) {
			result.setComments(new HashMap<Integer, String>());
		}
		return result.getComments();			
	}
	@PUT
	@Path("/{filmId}/comments/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteComment(@PathParam("filmId") int filmId, @PathParam("commentId") int commentId) {
		Film result = db.get(filmId);
		HashMap<Integer, String> comments = new HashMap<Integer, String>();
		if(result==null || result.getComments()==null || result.getComments().isEmpty()) {
			return Response.status(404).build();
		}
		comments = result.getComments();
		comments.remove(commentId);
		result.setComments(comments);
		db.updateComments(result);
		return Response.ok().build();
	}
	
	@POST
	@Path("/{filmId}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("filmId") int filmId, String comment) {
		Film result = db.get(filmId);
		int key;
		if(result==null) {
			return Response.status(404).build();
		}
		if(result.getComments()==null) {
			result.setComments(new HashMap<Integer, String>());
		}
		if(result.getComments().isEmpty()) {
			key = 0;
		}else {
			key = Collections.max(result.getComments().keySet());
		}
		result.getComments().put(++key, comment);
		return Response.ok().build();
	}

	@GET
	@Path("/{filmId}/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getActors(@PathParam("filmId") int filmId){
		Film result = db.get(filmId);
		if(result==null) {
			return null;
		}
		if(result.getActors()==null) {
			result.setActors(new ArrayList<Actor>());
		}
		return result.getActors();			
	}
	@POST
	@Path("/{id}/actors")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActor(@PathParam("id") int filmId, Actor actor) {
		Film result = db.get(filmId);
		if(result==null) {
			return Response.status(404).build();
		}
		if(result.getActors()==null) {
			result.setActors(new ArrayList<Actor>());
		}
		result.getActors().add(actor);
		return Response.ok().build();
	}
	@POST
	@Path("/{idFilm}/actors/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActor(@PathParam("idFilm") int filmId, @PathParam("id") int id) {
		Actor actor = dbActor.get(id);
		Film result = db.get(filmId);
		if(result==null || actor==null) {
			return Response.status(404).build();
		}
		if(result.getActors()==null) {
			result.setActors(new ArrayList<Actor>());
		}
		result.getActors().add(actor);
		return Response.ok().build();
	}
}

