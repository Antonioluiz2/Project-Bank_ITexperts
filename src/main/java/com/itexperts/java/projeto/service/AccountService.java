package com.itexperts.java.projeto.service;

import java.util.List;
import java.util.Optional;

//import javax.transaction.Transactional;
import com.itexperts.project.dto.account.AccountRequestDTO;
import com.itexperts.project.dto.account.AccountResponseDTO;
import com.itexperts.project.dto.cardtype.CardTypeRequestDTO;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itexperts.java.projeto.model.Account;
import com.itexperts.java.projeto.model.Card;
import com.itexperts.java.projeto.model.CardType;
import com.itexperts.java.projeto.repository.AccountRepository;
import com.itexperts.java.projeto.repository.CardRepository;
import com.itexperts.java.projeto.repository.CardTypeRepository;

@Service
public class AccountService {

    //TODO exceptions são lançadas aqui com try catch

	@Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardTypeRepository cardTypeRepository;

  //C- Create
    @Transactional
    public AccountResponseDTO create(AccountRequestDTO accountRequestDTO) {

        //TODO fazer validação caso lista de cartões esteja vazia! retonando optional -> evitar null pointer
        List<Card> cards = accountRequestDTO.getCards();
        Account accountPersisted;

        //verifica se conta passada na controller tem lista de cartões vazia
        if (accountRequestDTO.getCards().isEmpty()) {
            accountPersisted = accountRepository.save(convertDtoToEntity(accountRequestDTO));
            System.out.println("Creating account without card associated");
        } else {
            // atribuindo o objeto conta que vem da controller para o novo objeto
            Account accountToPersistFirst = convertDtoToEntity(accountRequestDTO);
            //
            accountToPersistFirst.setCards(null);
            accountPersisted = accountRepository.save(accountToPersistFirst);

            for (Card card : cards) {
                String typeCardName = card.getCardType().getName();

                //TODO refatorar para pegar metodo especifico com unica responsabilidade de ver se type card existe no banco (service type card)
                Optional<CardType> cardTypeReturned = cardTypeRepository.findByName(typeCardName);
                cardTypeReturned.orElseThrow(() -> new RuntimeException("Card type not found."));

                card.setCardType(cardTypeReturned.get());
                card.setAccount(accountPersisted);
                cardRepository.save(card);
            }
            //só card.setAccount ja basta para salvar os cartões na conta lá no BD
            //linha abaixo serve pra atualizar o objeto do java com os cartões, para nao voltar nulo
            accountPersisted.setCards(cards);
        }

        //pq n precisa dar save de novo na conta?

        return convertEntityToDTO(accountPersisted);
    }

    //TODO exception se nao tiver nenhum card sendo passado
    //TODO exception se tiver um card igual ja cadastrado
    //TODO problema com DTO Response: construtor Account recebendo Card e tipo card
    //TODO ver problema de associação bidirecional de retornar nulo
    //TODO refatorar em dois métodos o método create

    //R - Read All com List
    //caso lista esteja vazia o código abaixo retorna um array vazio com cod 200 OK
    @Transactional
    public List<AccountResponseDTO> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountService::convertEntityToDTO)
                .sorted(comparing(AccountResponseDTO::getId))
                .collect(toList());
    }

    //R - Read All com Pageable
    @Transactional
    public Page<AccountResponseDTO> getAllPageable(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(AccountService::convertEntityToDTO);
    }

    //R - Read One by Id
    @Transactional
    public AccountResponseDTO getById(Integer id) {

        Optional<Account> accountReturned = accountRepository.findById(id);
        accountReturned.orElseThrow(() -> new RuntimeException("Account not found."));

        return convertEntityToDTO(accountReturned.get());
    }

    //R - Read One by RegisterId
    @Transactional
    public AccountResponseDTO getByRegisterId(String registerId) {

        Optional<Account> accountReturned = accountRepository.findByRegisterId(registerId);
        accountReturned.orElseThrow(() -> new RuntimeException("Account not found"));

        return convertEntityToDTO(accountReturned.get());
    }

    //TODO é possível fazer update com vargars?

    //U - Update objeto inteiro by Id
    @Transactional
    public AccountResponseDTO updateById(Integer id, AccountRequestDTO accountRequestDTO) {

        Optional<Account> accountCheck = accountRepository.findById(id);
        accountCheck.orElseThrow(() -> new RuntimeException("Account not found"));

        //TODO validação pra nao deixar entrar duplicado
        //java.sql.SQLIntegrityConstraintViolationException
        accountCheck.get().setNameOwner(accountRequestDTO.getNameOwner());
        accountCheck.get().setAgencyCode(accountRequestDTO.getAgencyCode());
        accountCheck.get().setAccountCode(accountRequestDTO.getAccountCode());
        accountCheck.get().setVerificationDigit(accountRequestDTO.getVerificationDigit());
        accountCheck.get().setRegisterId(accountRequestDTO.getRegisterId());
        accountCheck.get().setCards(accountRequestDTO.getCards());
        Account accountUpdated = accountRepository.save(accountCheck.get());

        return convertEntityToDTO(accountUpdated);
    }

    //U - Update by Name
    @Transactional
    public AccountResponseDTO updateByRegisterId(String registerId, AccountRequestDTO accountRequestDTO) {

        Optional<Account> accountCheck = accountRepository.findByRegisterId(registerId);
        accountCheck.orElseThrow(() -> new RuntimeException("Account not found"));

        //TODO validação pra nao deixar entrar duplicado
        //java.sql.SQLIntegrityConstraintViolationException
        accountCheck.get().setNameOwner(accountRequestDTO.getNameOwner());
        accountCheck.get().setAgencyCode(accountRequestDTO.getAgencyCode());
        accountCheck.get().setAccountCode(accountRequestDTO.getAccountCode());
        accountCheck.get().setVerificationDigit(accountRequestDTO.getVerificationDigit());
        accountCheck.get().setCards(accountRequestDTO.getCards());
        //nao deixa editar register id quando se pesquisar pelo register id
        Account accountUpdated = accountRepository.save(accountCheck.get());

        return convertEntityToDTO(accountUpdated);
    }

    //TODO delete não pode deixar deletar conta até que todos os cartões tenham sido deletados primeiro. response com log de quantos cartões ainda existem na base na hora de erro ao tentar deletar uma conta com cards
    // TODO delete from account where id = 3	Error Code: 1451. Cannot delete or update a parent row: a foreign key constraint fails (`itexperts`.`card`, CONSTRAINT `fk_card_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`))	0.062 sec
    //D - Delete By Id
    @Transactional
    public void deleteById(Integer id) {

        Optional<Account> accountReturned = accountRepository.findById(id);
        accountReturned.orElseThrow(() -> new RuntimeException("Account not found."));

        accountRepository.deleteById(accountReturned.get().getId());

    }

    //D - Delete By Register Id
    @Transactional
    public void deleteByRegisterId(String registerId) {

        Optional<Account> accountReturned = accountRepository.findByRegisterId(registerId);
        accountReturned.orElseThrow(() -> new RuntimeException("Account not found."));

        accountRepository.deleteById(accountReturned.get().getId());

    }


    //TODO delete de cartao nao pode apagar os types q eles referenciavam!!! -> por isso n usa o cascade all?

    //***********************  Converters  **********************************

    private static AccountResponseDTO convertEntityToDTO(Account accountEntity) {

        AccountResponseDTO accountDTO = new AccountResponseDTO();
        accountDTO.setId(accountEntity.getId());
        accountDTO.setNameOwner(accountEntity.getNameOwner());
        accountDTO.setAgencyCode(accountEntity.getAgencyCode());
        accountDTO.setAccountCode(accountEntity.getAccountCode());
        accountDTO.setVerificationDigit(accountEntity.getVerificationDigit());
        accountDTO.setRegisterId(accountEntity.getRegisterId());
        accountDTO.setCards(accountEntity.getCards());

        return accountDTO;
    }

    private Account convertDtoToEntity(AccountRequestDTO accountDTO) {

        Account accountEntity = new Account();
        accountEntity.setNameOwner(accountDTO.getNameOwner());
        accountEntity.setAgencyCode(accountDTO.getAgencyCode());
        accountEntity.setAccountCode(accountDTO.getAccountCode());
        accountEntity.setVerificationDigit(accountDTO.getVerificationDigit());
        accountEntity.setRegisterId(accountDTO.getRegisterId());
        accountEntity.setCards(accountDTO.getCards());

        return accountEntity;
    }
}
