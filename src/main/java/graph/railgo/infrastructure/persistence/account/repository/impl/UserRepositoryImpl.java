package graph.railgo.infrastructure.persistence.account.repository.impl;


import graph.railgo.domain.account.model.User;
import graph.railgo.domain.account.repository.UserRepository;
import graph.railgo.infrastructure.persistence.account.mapper.UserEntityMapper;
import graph.railgo.infrastructure.persistence.account.model.UserEntity;
import graph.railgo.infrastructure.persistence.account.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserEntityRepository repository;
    private final UserEntityMapper mapper;

    @Autowired
    public UserRepositoryImpl(UserEntityRepository repository,UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return repository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return Optional.empty();
        }
        Optional<UserEntity> userEntity = repository.findByPhoneNumber(phoneNumber);
        return userEntity.map(mapper::toDTO);
    }

    @Override
    public Optional<User> findById(String id){
        if (id == null) {
            return Optional.empty();
        }
        return repository.findById(id)
                .map(mapper::toDTO);
    }
    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        repository.save(userEntity);
        return mapper.toDTO(userEntity);
    };
}
