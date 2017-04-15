package org.deltaa.superrduperr.service;

import java.util.List;

import org.deltaa.superrduperr.model.ToDoItem;

public interface ToDoItemService {

	List<ToDoItem> getAllToDoList(String status, Boolean isDeleted, String tag, String fromDate, String toDate);

	ToDoItem getItem(String itemId);

	ToDoItem addToDoItem(ToDoItem toDoItem);

	ToDoItem updateToDoItem(ToDoItem toDoItem, String itemId);

	boolean updateItemAsDelete(String itemId);

	ToDoItem addTagtoItem(String itemId, String tag);

	ToDoItem addReminderToItem(String itemId, String reminderDate);

	ToDoItem restoreItem(String itemId);

	ToDoItem updateStatusAsComplete(String itemId);

}
