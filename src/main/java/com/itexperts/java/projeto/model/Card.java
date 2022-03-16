package com.itexperts.java.projeto.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itexperts.java.projeto.enums.Flag;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "flag")
    @Enumerated(EnumType.STRING)
    private Flag flag;

    @ManyToOne
    @JoinColumn(name = "card_type_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_card_type"), unique = true)
    //dá erro sem excluir o toString do @Data
    @ToString.Exclude
    private CardType cardType;

    //TODO deixar numero unico
    @Column(name = "number")
    private String number;

    @Column(name = "digit_code")
    private String digitCode;

    @Column(name = "limit_balance")
    private Double limitBalance;

    //TODO problema quando adiciona response DTO retorna infinito
    //TODO problema de bidirecional, talvez não tenha necessidade de ter objeto de account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_card_account"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //dá erro sem excluir o toString do @Data
    @ToString.Exclude
    private Account account;
}
