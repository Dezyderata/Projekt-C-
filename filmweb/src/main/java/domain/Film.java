package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Film {
	private int id;
	private String title;
	private String informations;
	private HashMap<Integer, String> comments = new HashMap<Integer, String>();
	private List<Actor> actors = new ArrayList<Actor>();
	private float note;
	
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HashMap<Integer, String> getComments() {
		return comments;
	}
	public void setComments(HashMap<Integer, String> comments) {
		this.comments = comments;
	}
	public List<Actor> getActors() {
		return actors;
	}
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	public String getInformations() {
		return informations;
	}
	public void setInformations(String informations) {
		this.informations = informations;
	}
}
