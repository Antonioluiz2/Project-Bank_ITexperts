package com.itexperts.java.projeto.model;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itexperts.java.projeto.enums.Flag;

import lombok.Data;


@SuppressWarnings("serial")
@Entity
@Table(name = "card")
@Data

public class Cardd implements Serializable {
	
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "flag")
	@Enumerated(EnumType.STRING)
	private Flag flag;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true, foreignKey = @ForeignKey(name = "fk_card_type"))
	// @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private CardType cardType;

	// TODO deixar numero unico
	@Column(name = "number")
	private String number;

	@Column(name = "digit_code")
	private String digitCode;

	@Column(name = "limit_balance")
	private Double limitBalance;

	// TODO problema quando adiciona response DTO retorna infinito
	// TODO problema de bidirecional, talvez não tenha necessidade de ter objeto de
	// account
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_card_account"))
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Account account;
}
