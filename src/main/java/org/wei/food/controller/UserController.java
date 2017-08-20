package org.wei.food.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wei.food.domain.UserInfo;
import org.wei.food.repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Controller
@Transactional
@Api(description = "用户信息管理")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session 管理session
     * @return ResMsg
     */
    @ResponseBody
    @RequestMapping(value = "/session", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录",httpMethod = "POST")
    public ResMsg userLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
        UserInfo userInfo = userRepository.findByUsernameAndPassword(username, password);
        if (userInfo == null) {
            return new ResMsg(-1, "用户名或密码错误", null);
        }
        session.setAttribute("username",username);
        return new ResMsg(0, "登录成功", username);
    }

    /**
     * 添加或修改新用户
     * @param userInfo 用户信息
     * @return ResMsg
     */
    @ResponseBody
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    @ApiOperation(value = "添加或修改新用户",httpMethod = "POST")
    public ResMsg addUser(@RequestBody UserInfo userInfo){
        userRepository.save(userInfo);
        return new ResMsg(0,"操作用户成功",userInfo.getUsername());
    }
}
