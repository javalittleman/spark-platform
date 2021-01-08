package com.spark.platform.wx.shop.biz.api.service;

import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.user.*;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 20:48
 * @Description: 用户信息
 */
public interface ApiUserService {
    /**
     * 登录
     * @param loginDTO
     * @return
     */
    ShopUser login(WxLoginDTO loginDTO);

    /**
     * 获取用户的地址
     * @param userId
     * @return
     */
    List<ShopUserAddress> findAddress(Integer userId);

    /**
     * 保存用户
     * @param shopUserAddress
     * @return
     */
    boolean saveAddress(ShopUserAddress shopUserAddress);


    /**
     * 获取用户的购物车
     * @param userId
     * @return
     */
    List<ShopUserCart> findCart(Integer userId);
}
