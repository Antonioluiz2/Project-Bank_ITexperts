package com.itexperts.java.projeto.dto.account;

import java.util.List;

import com.itexperts.java.projeto.model.Card;

import lombok.Data;

@Data
public class AccountResponseDTO {
	private Integer id;
    private String nameOwner;
    private String agencyCode;
    private String accountCode;
    private String verificationDigit;
    private String registerId;
    private List<Card> cards;
}
