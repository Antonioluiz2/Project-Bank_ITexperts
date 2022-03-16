package com.itexperts.java.projeto.dto.account;

import lombok.Data;

@Data
public class AccountDTO {
	private Integer id;
    private String nameOwner;
    private String agencyCode;
    private String accountCode;
    private String verificationDigit;
    private String registerId;
}
