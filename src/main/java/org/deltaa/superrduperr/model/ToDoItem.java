package org.deltaa.superrduperr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.deltaa.superrduperr.util.JsonDateDeSerializer;
import org.deltaa.superrduperr.util.JsonDateSerializer;
import org.deltaa.superrduperr.util.Status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Ashish Gajabi
 * Model class for TODOItems
 */
@Entity
@XmlRootElement
public class ToDoItem implements Serializable {	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToDoItem() {
		super();
	}

	public ToDoItem(String name) {
		super();
		this.name = name;
		this.itemStatus = Status.Open;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private Status itemStatus;
	
	private boolean isDeleted;
	
	@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize (using=JsonDateDeSerializer.class)
	@Temporal (TemporalType.DATE)
	private Date reminderDate;
	
	@ElementCollection 
	@JoinTable(joinColumns = @JoinColumn(name = "id"))
	@Fetch (FetchMode.JOIN)
	private Set<String> tags;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Status itemStatus) {
		this.itemStatus = itemStatus;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}
	
	public Set<String> getTags() {
		return tags;
	}

	public void addTags(String tag) {
		if(this.tags == null) {
			this.tags = new HashSet<String> ();
		}
		this.tags.add(tag);
	}
	
	
	

}
