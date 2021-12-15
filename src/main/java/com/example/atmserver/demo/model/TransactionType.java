package com.example.atmserver.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Exercise type
 */
public enum TransactionType {

	CASH_DEPOSIT("CASH_DEPOSIT"),

	CASH_WITHDRAW("CASH_WITHDRAW")	;

	private final String value;

	TransactionType(final String value) {
		this.value = value;
	}

	@JsonCreator
	public static TransactionType fromValue(final String value) {
		for (final TransactionType b : TransactionType.values()) {
			if (b.value.equals(value)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}



}

