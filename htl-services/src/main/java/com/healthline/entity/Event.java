package com.healthline.entity;

/**
 * 
 * @author 212473687
 *
 */
public class Event {

	private Media media;

	private Description text;

	private EventDate eventDate;
	
	public EventDate getEventDate() {
		return eventDate;
	}


	public void setEventDate(EventDate eventDate) {
		this.eventDate = eventDate;
	}


	public Event() {
		// TODO Auto-generated constructor stub
	}
	

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Description getText() {
		return text;
	}

	public void setText(Description text) {
		this.text = text;
	}

	

}
