package com.itexperts.java.projeto.dto.card;

import java.math.BigDecimal;

import com.itexperts.java.projeto.enums.Flag;
import com.itexperts.java.projeto.model.CardType;
import lombok.Data;

@Data
public class CardResponseDTO {

    private Integer id;
    private String name;
    private Flag flag;
    private CardType type; //ou CardTypeResponseDTO?
    private String number;
    private String digitCode;
    private BigDecimal limitBalance;
    private Integer accountId; //ou AccountDTO?
}
