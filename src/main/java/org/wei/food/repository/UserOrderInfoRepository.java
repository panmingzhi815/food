package org.wei.food.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wei.food.domain.FootInfo;
import org.wei.food.domain.UserOrderInfo;

@Repository
public interface UserOrderInfoRepository extends PagingAndSortingRepository<UserOrderInfo,Long> {


}
