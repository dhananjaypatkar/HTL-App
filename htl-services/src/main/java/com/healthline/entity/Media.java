package com.healthline.entity;

/**
 * 
 * @author 212473687
 *
 */
public class Media {

	private String url;
	private String caption;
	private String credit;

	public Media() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return The url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url
	 *            The url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @return The caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * 
	 * @param caption
	 *            The caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * 
	 * @return The credit
	 */
	public String getCredit() {
		return credit;
	}

	/**
	 * 
	 * @param credit
	 *            The credit
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}

}
