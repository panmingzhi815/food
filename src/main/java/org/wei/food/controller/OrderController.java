package org.wei.food.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wei.food.domain.FootInfo;
import org.wei.food.domain.OrderFoot;
import org.wei.food.domain.OrderInfo;
import org.wei.food.domain.UserOrderInfo;
import org.wei.food.repository.FootInfoRepository;
import org.wei.food.repository.OrderInfoRepository;
import org.wei.food.repository.UserOrderInfoRepository;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@Transactional
@Api(description = "订单信息管理")
public class OrderController {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private FootInfoRepository footInfoRepository;

    @Autowired
    private UserOrderInfoRepository userOrderInfoRepository;

    /**
     * 获取订单列表
     * @return ResMsg
     */
    @ApiOperation(value = "获取订单列表",httpMethod = "GET")
    @RequestMapping("/order/list")
    public ResMsg getOrderInfoList(){
        List<OrderInfo> orderInfoList = orderInfoRepository.findByOrderPay(false);
        return ResMsg.success(0,orderInfoList);
    }

    /**
     * 添加或更新订单
     * @param orderInfo 订单信息
     * @param orderFootList 订单食物列表
     * @return ResMsg
     */
    @ApiOperation(value = "添加或更新订单",httpMethod = "POST")
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public ResMsg addOrderInfo(OrderInfo orderInfo,List<OrderFoot> orderFootList){
        orderInfo.setOrderFootList(orderFootList);
        orderInfo.setOrderPrice(orderFootList.stream().mapToDouble(OrderFoot::getPrice).reduce(0D,(x, y)->x + y));
        OrderInfo save = orderInfoRepository.save(orderInfo);
        return ResMsg.success(0,save.getId());
    }

    /**
     * 获取订单中己选择的菜
     * @param id 订单id
     * @return ResMsg
     */
    @ApiOperation(value = "获取订单中己选择的菜",httpMethod = "GET")
    @RequestMapping(value = "/order/foot/{id}",method = RequestMethod.GET)
    public ResMsg getOrderFootList(@PathVariable("id") Long id){
        OrderInfo one = orderInfoRepository.findOne(id);
        if (one != null) {
            return ResMsg.success(0,one.getOrderFootList());
        }
        return ResMsg.fail(-1,"未找到相应的订单");
    }

    /**
     * 取消订单
     * @return ResMsg
     */
    @ApiOperation(value = "取消订单",httpMethod = "DELETE")
    @RequestMapping(value = "/order/{id}",method = RequestMethod.DELETE)
    public ResMsg removeOrderInfo(@PathVariable("id") Long id){
        orderInfoRepository.delete(id);
        return ResMsg.success(0,id);
    }

    /**
     * 获取所有的菜
     * @return ResMsg
     */
    @ApiOperation(value = "获取所有的菜",httpMethod = "GET")
    @RequestMapping(value = "/foot/list",method = RequestMethod.GET)
    public ResMsg getFootInfoList(){
        Iterable<FootInfo> repositoryAll = footInfoRepository.findAll(new Sort(Sort.Direction.ASC, "footType", "footName"));
        return ResMsg.success(0,repositoryAll);
    }

    /**
     * 获取总共订单金额
     * @return ResMsg
     */
    @ApiOperation(value = "获取总共订单金额",httpMethod = "GET")
    @RequestMapping(value = "/order/money",method = RequestMethod.GET)
    public ResMsg getTotalOrderMoney(){
        Double sumOrderPrice = orderInfoRepository.sumOrderPrice();
        return ResMsg.success(0,sumOrderPrice);
    }

    /**
     * 订单入账
     * @return ResMsg
     */
    @ApiOperation(value = "订单入账",httpMethod = "POST")
    @RequestMapping(value = "/order/money",method = RequestMethod.POST)
    public ResMsg updateTotalOrderMoney() {
        UserOrderInfo userOrderInfo = new UserOrderInfo();
        Double sumOrderPrice = orderInfoRepository.sumOrderPrice();
        userOrderInfo.setUserOrderMoney(sumOrderPrice);
        List<OrderInfo> orderInfoList = orderInfoRepository.findByUserOrderInfoIsNull();
        userOrderInfo.setUserOrderSize(orderInfoList.size());
        userOrderInfoRepository.save(userOrderInfo);

        for (OrderInfo orderInfo : orderInfoList) {
            orderInfo.setUserOrderInfo(userOrderInfo);
            orderInfoRepository.save(orderInfo);
        }

        return ResMsg.success(0,"入账成功");
    }

}