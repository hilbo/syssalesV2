package com.system.syssalesv2.util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CalculatorOfDue implements Serializable{
	private static final long serialVersionUID = 1L;

	public static LocalDateTime calculateDueDate(LocalDateTime currentDate) {
		LocalDateTime dueDate;
		dueDate = currentDate.plusDays(1);
		return dueDate;
	}
}
