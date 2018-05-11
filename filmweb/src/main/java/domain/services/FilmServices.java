package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Film;


public class FilmServices {
	private static List<Film> db = new ArrayList<Film>();
	private static int currentId = 1;
	private static int note = 0;
	private static int numberOfNotes = 0;
 
	public void updateNote(Film film, int newNote) {
		if(newNote>=0 && newNote<=10) {
			note = note + newNote;
			++numberOfNotes;
			for(Film f : db) {
				if(f.getId()==film.getId()) {
					f.setNote((float)note/(float)numberOfNotes);
				}
			}
		
		}
		
	}
	public List<Film> getAll(){
		return db;
	}
	public Film get(int id) {
		for(Film p : db) {
			if(p.getId()==id) {
				return p;
			}
		}
		return null;
	}
	public void add(Film f) {
		f.setId(++currentId);
		db.add(f);
	}
	public void update(Film film) {
		for(Film f : db) {
			if(f.getId()==film.getId()) {
				f.setTitle(film.getTitle());
				f.setInformations(film.getInformations());
			}
		}
	}
	public void updateComments(Film film) {
		for(Film f : db) {
			if(f.getId()==film.getId()) {
				f.setComments(film.getComments());
			}
		}
	}
	public void delete(Film f) {
		db.remove(f);
	}
}
