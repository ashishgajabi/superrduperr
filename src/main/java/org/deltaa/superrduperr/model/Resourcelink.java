package org.deltaa.superrduperr.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ashish Gajabi
 * model class for rellink details
 */
@XmlRootElement
public class Resourcelink {

	public Resourcelink(String href, String rel, String example) {
		super();
		this.rel = rel;
		this.href = href;
		this.example = example;
	}
	
	public Resourcelink() {
		super();
	}

	private String rel;
	private String href;
	private String example;
	
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

}
