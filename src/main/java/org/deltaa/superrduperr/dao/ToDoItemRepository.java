package org.deltaa.superrduperr.dao;

import java.util.Date;
import java.util.List;

import org.deltaa.superrduperr.model.ToDoItem;

public interface ToDoItemRepository {

	List<ToDoItem> getAllToDoList();

	ToDoItem getItem(Integer itemId);

	ToDoItem addToDoItem(ToDoItem toDoItem);

	ToDoItem updateToDoItem(ToDoItem toDoItem);

	ToDoItem addTagtoItem(Integer itemId, String tag);

	ToDoItem addReminderToItem(Integer itemId, Date reminderDate);

	ToDoItem restoreItem(Integer itemId);

	ToDoItem updateStatusAsComplete(Integer itemId);

	boolean updateItemAsDelete(Integer itemId);

}
