package com.example.restGameDemo.domain;

public class Game {

	private long id;
	private String title;
	private String directorName;
	private String directorSurname;
	private String company;
	private String price;

	public Game() {
	}

	public Game(String title, String directorName, String directorSurname, String company, String price) {
		super();
		this.title = title;
		this.directorName = directorName;
		this.directorSurname = directorSurname;
		this.company = company;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getDirectorSurname() {
		return directorSurname;
	}

	public void setDirectorSurname(String directorSurname) {
		this.directorSurname = directorSurname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


}
