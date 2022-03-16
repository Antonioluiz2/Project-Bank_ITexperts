package com.itexperts.java.projeto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

//import org.hibernate.annotations.Table;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Accountt implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//TODO entender o implements serializable do JPA (especificação) / Hibernate (implementação padrão da espec. JPA)
	    // serializable converte em bytes pra encaminhar/recuperar dados para o/do banco de dados

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Integer id;

	    @Column(name = "name_owner")
	    private String nameOwner;

	    @Column(name = "agency_code")
	    private String agencyCode;

	    @Column(name = "account_code")
	    private String accountCode;

	    @Column(name = "digit_verification")
	    private String verificationDigit;

	    //TODO deve ser unico, tipo CPF
	    @Column(name = "register_id")
	    private String registerId;

	    //TODO indicar que nao pdoe deletar conta quando tiver cartao associado - Account
	    //TODO deixar somente esse relacionamento + join column
	    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	    private List<Card> cards;
}
