package org.wei.food.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wei.food.domain.UserInfo;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserInfo,Long> {

    public UserInfo findByUsernameAndPassword(String username,String password);

}
