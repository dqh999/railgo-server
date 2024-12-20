package graph.railgo.domain.account.service;


import graph.railgo.domain.account.model.User;

public interface IUserService {
    User register(User user);
    User login(String phoneNumber,String password);

    User changePassword(String phoneNumber,String oldPassword,String newPassword);

    User getUserByPhoneNumber(String phoneNumber);
}
