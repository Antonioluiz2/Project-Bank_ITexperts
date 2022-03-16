package com.itexperts.java.projeto.model;

import java.io.Serializable;

import javax.persistence.*;

import com.itexperts.java.projeto.dto.cardtype.CardTypeRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type", indexes = { @Index(name = "type_card_name_index", columnList = "name", unique = true) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public CardType(CardTypeRequestDTO cardTypeRequestDTO) {
		this.name = cardTypeRequestDTO.getName();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;
}
