package graph.railgo.domain.account.repository;


import graph.railgo.domain.account.model.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository {
    void save(Token t);
    void saveAll(List<Token> t);
    Optional<Token> findByValue(String value);
    List<Token> findByAccountIdAndRevokedFalse(String accountId);
}
