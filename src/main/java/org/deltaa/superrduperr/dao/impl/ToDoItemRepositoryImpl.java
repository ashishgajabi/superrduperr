package org.deltaa.superrduperr.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.deltaa.superrduperr.dao.ToDoItemRepository;
import org.deltaa.superrduperr.model.ToDoItem;
import org.deltaa.superrduperr.util.Status;
import org.springframework.stereotype.Repository;

/**
 * @author Ashish Gajabi
 * Implementation class for CRUD methods
 */
@Repository
public class ToDoItemRepositoryImpl implements ToDoItemRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ToDoItem> getAllToDoList() {
		Query query = entityManager.createQuery("select t from ToDoItem t");
		return query.getResultList();
	}

	@Override
	public ToDoItem addToDoItem(ToDoItem toDoItem) {
		entityManager.persist(toDoItem);
		entityManager.flush();
		return toDoItem;
	}

	private boolean update(ToDoItem t) {
		if (t != null) {
			entityManager.merge(t);
			return true;
		}
		return false;
	}

	@Override
	public ToDoItem updateStatusAsComplete(Integer itemId) {
		ToDoItem toDoItem = getItem(itemId);
		if (toDoItem != null) {
			toDoItem.setItemStatus(Status.Completed);
			if (update(toDoItem)) {
				return toDoItem;
			} else {
				throw new InternalServerErrorException();
			}
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public boolean updateItemAsDelete(Integer itemId) {
		ToDoItem toDoItem = getItem(itemId);
		if (toDoItem != null) {
			toDoItem.setDeleted(true);
			return update(toDoItem);
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public ToDoItem restoreItem(Integer itemId) {
		ToDoItem toDoItem = getItem(itemId);
		if (toDoItem != null) {
			toDoItem.setDeleted(false);
			if (update(toDoItem)) {
				return toDoItem;
			} else {
				throw new InternalServerErrorException();
			}
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public ToDoItem addReminderToItem(Integer itemId, Date reminderDate) {
		ToDoItem toDoItem = getItem(itemId);
		if (toDoItem != null) {
			toDoItem.setReminderDate(reminderDate);
			if (update(toDoItem)) {
				return toDoItem;
			} else {
				throw new InternalServerErrorException();
			}
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public ToDoItem addTagtoItem(Integer itemId, String tag) {
		ToDoItem toDoItem = getItem(itemId);
		if (toDoItem != null) {
			toDoItem.addTags(tag);
			if (update(toDoItem)) {
				return toDoItem;
			} else {
				throw new InternalServerErrorException();
			}
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public ToDoItem getItem(Integer itemId) {
		return entityManager.find(ToDoItem.class, itemId);
	}

	@Override
	public ToDoItem updateToDoItem(ToDoItem toDoItem) {
		ToDoItem existingToDoItem = getItem(toDoItem.getId());
		if (existingToDoItem != null) {
			if (update(toDoItem)) {
				return toDoItem;
			} else {
				throw new InternalServerErrorException();
			}
		} else {
			throw new NotFoundException();
		}
	}

}
