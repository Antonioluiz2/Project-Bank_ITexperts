package com.itexperts.java.projeto.dto.card;

import java.math.BigDecimal;

import com.itexperts.java.projeto.enums.Flag;

import lombok.Data;

@Data
public class CardRequestDTO {
	private String name;
    private Flag flag;
    private String type;
    private String number;
    private String digitCode;
    private BigDecimal limitBalance;

}
