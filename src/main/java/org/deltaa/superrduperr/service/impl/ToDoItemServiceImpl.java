package org.deltaa.superrduperr.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.BadRequestException;

import org.deltaa.superrduperr.dao.ToDoItemRepository;
import org.deltaa.superrduperr.exception.InvalidRequestException;
import org.deltaa.superrduperr.model.ToDoItem;
import org.deltaa.superrduperr.service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ashish Gajabi
 * Service class implementation
 */

@Service
@Transactional
public class ToDoItemServiceImpl implements ToDoItemService {
	
	@Autowired
	private ToDoItemRepository toDoItemRepository;
	
	public ToDoItemServiceImpl() {
	}

	@Override
	public List<ToDoItem> getAllToDoList(String status, Boolean isDeleted, String tag, String fromDate, String toDate) {
		List<ToDoItem> toDoItemList = toDoItemRepository.getAllToDoList();
		if(toDoItemList == null || toDoItemList.size() == 0) {
			return null;
		}
		if(status != null) {
			toDoItemList = toDoItemList.stream().filter(e -> e.getItemStatus().status.equalsIgnoreCase(status)).collect(Collectors.<ToDoItem>toList());
		}
		if(toDoItemList.size() != 0 && isDeleted != null) {
			toDoItemList = toDoItemList.stream().filter(e -> e.isDeleted() == isDeleted).collect(Collectors.<ToDoItem>toList());
		}
		if(toDoItemList.size() != 0 && tag != null) {
			toDoItemList = toDoItemList.stream().filter(e -> e.getTags().contains(tag)).collect(Collectors.<ToDoItem>toList());
		}
		if(toDoItemList.size() != 0 && toDate != null) {
			toDoItemList = filterBasedOnDate (toDoItemList, fromDate, toDate);
		}		
		return toDoItemList;
	}

	private List<ToDoItem> filterBasedOnDate(List<ToDoItem> toDoItemList, String fromDate, String toDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");	
		final Date upperDate;
		final Date lowerDate;		
		try {
			
			if(fromDate == null) {
				lowerDate = formatter.parse((new Date()).toString());
			} else {
				lowerDate = formatter.parse(fromDate);
			}
			upperDate = formatter.parse(toDate);	
		} catch (ParseException e) {
			throw new InvalidRequestException("Invalid dates. Please enter only in 'ddMMyyyy' format e.g. 25072017");
		}
		return (toDoItemList.stream().filter(e -> (e.getReminderDate() != null 
															&& e.getReminderDate().after(lowerDate) 
															&& e.getReminderDate().before(upperDate)))
															.collect(Collectors.<ToDoItem>toList()));
	}

	@Override
	public ToDoItem addToDoItem(final ToDoItem toDoItem) {
		return toDoItemRepository.addToDoItem(toDoItem);
	}

	@Override
	public ToDoItem updateToDoItem(ToDoItem toDoItem, String itemId) {
		if(toDoItem == null) {
			throw new BadRequestException();
		}
		toDoItem.setId(ConvertToNumber(itemId));
		return toDoItemRepository.updateToDoItem(toDoItem);
	}

	@Override
	public boolean updateItemAsDelete(String itemId) {
		return toDoItemRepository.updateItemAsDelete(ConvertToNumber(itemId));
	}

	@Override
	public ToDoItem getItem(String itemId) {
		return toDoItemRepository.getItem(ConvertToNumber(itemId));
	}

	@Override
	public ToDoItem addTagtoItem(String itemId, String tag) {
		return toDoItemRepository.addTagtoItem(ConvertToNumber(itemId), tag);
	}

	@Override
	public ToDoItem addReminderToItem(String itemId, String reminderDate) {
		Date date = null;
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		try {
			date = formatter.parse(reminderDate);
		} catch (ParseException e) {
			throw new InvalidRequestException("Invalid reminder date. Please enter only in 'ddMMyyyy' format e.g. /25072017");
		}
		if(date.before(currentDate)) {
			throw new InvalidRequestException("Invalid reminder date. Please enter future date ('ddMMyyyy') only");
		}
		return toDoItemRepository.addReminderToItem(ConvertToNumber(itemId), date);
	}

	@Override
	public ToDoItem restoreItem(String itemId) {
		return toDoItemRepository.restoreItem(ConvertToNumber(itemId));
	}

	@Override
	public ToDoItem updateStatusAsComplete(String itemId) {
		return toDoItemRepository.updateStatusAsComplete(ConvertToNumber(itemId));
	}
	
	private Integer ConvertToNumber(String itemId) {
		try
		{
			return Integer.parseInt(itemId);
		}
		catch (NumberFormatException e)
		{
			throw new InvalidRequestException("The request is invalid.");
		}		
	}

}
