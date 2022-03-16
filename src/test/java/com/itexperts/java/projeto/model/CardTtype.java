package com.itexperts.java.projeto.model;

import java.io.Serializable;

import javax.persistence.*;

import com.itexperts.java.projeto.enums.CardTypeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
public class CardTtype implements Serializable{
	private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
    private CardTypeEnum name;
}
