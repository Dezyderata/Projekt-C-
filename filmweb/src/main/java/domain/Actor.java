package domain;

import java.util.ArrayList;
import java.util.List;

public class Actor {
	private int id;
	private String name;
	private String surname;
	private List<Film> Films = new ArrayList<Film>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public List<Film> getFilms() {
		return Films;
	}
	public void setFilms(List<Film> films) {
		Films = films;
	}
}
