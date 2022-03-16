package com.itexperts.java.projeto.dto.cardtype;

import lombok.Data;

@Data
public class CardTypeRequestDTO {
	 //TODO validação not null
    //TODO validação do tipo de nome - se é maiúsculo, se é um tipo válido, se não tem caracteres especiais, a nao ser underline (Regex A-Z + underline)
    //TODO tamanho valido
    private String name;

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
