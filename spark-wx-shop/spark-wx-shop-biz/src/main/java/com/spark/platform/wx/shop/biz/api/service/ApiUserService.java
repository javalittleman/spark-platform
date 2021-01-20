package com.spark.platform.wx.shop.biz.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spark.platform.wx.shop.api.dto.UserCartDTO;
import com.spark.platform.wx.shop.api.dto.WxLoginDTO;
import com.spark.platform.wx.shop.api.entity.user.*;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author: wangdingfeng
 * @Date: 2020/12/13 20:48
 * @Description: 用户信息
 */
public interface ApiUserService {
    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    ShopUser login(WxLoginDTO loginDTO);

    /**
     * 保存用户手机号
     *
     * @param userId 用户
     * @param mobile 手机号
     * @return
     */
    boolean saveMobile(Integer userId, String mobile);

    /**
     * 分页展示用户收藏
     * @param userId
     * @param size
     * @param userId
     * @return
     */
    IPage<ShopUserCollect> pageCollect(long current, long size, Integer userId);

    /**
     * 保存用户收藏信息
     * @param userId
     * @param goodsId
     * @return
     */
    boolean saveCollect(Integer userId, Integer goodsId);

    /**
     * 批量删除 收藏信息
     * @param ids
     * @return
     */
    boolean delCollects(List<Integer> ids);

    /**
     * 分页展示用户足迹
     * @param current
     * @param size
     * @param userId
     * @return
     */
    IPage<ShopUserFootprint> pageFootprint(long current, long size, Integer userId);

    /**
     * 删除用户足迹信息
     * @param ids
     * @return
     */
    boolean delFootprints(List<Integer> ids);

    /**
     * 提交购物车
     * @param userCartDTO
     * @return
     */
    boolean submitCart(UserCartDTO userCartDTO);

    /**
     * 分页展示购物车
     * @param current
     * @param size
     * @param userId
     * @return
     */
    IPage<ShopUserCart> pageCart(long current, long size, Integer userId);

    /**
     * 删除用户足迹信息
     * @param ids
     * @return
     */
    boolean delCart(List<Integer> ids);

}