package com.spark.platform.wx.shop.api.vo;

import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsAttr;
import com.spark.platform.wx.shop.api.entity.goods.ShopGoodsSku;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ProjectName: spark-platform
 * @Package: com.spark.platform.wx.shop.api.vo
 * @ClassName: GoodsDetailVo
 * @Author: wangdingfeng
 * @Description: 商品详情数据
 * @Date: 2020/12/23 14:23
 * @Version: 1.0
 */
@Data
@ApiModel(value = "GoodsDetailVo对象", description = "商品详情查询")
public class GoodsDetailVo {
    @ApiModelProperty(value = "商品主键")
    private Integer goodsId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "简介")
    private String brief;
    @ApiModelProperty(value = "图片")
    private String homePic;
    @ApiModelProperty(value = "商品主图")
    private List<String> picList;
    @ApiModelProperty(value = "单位")
    private String unit;
    @ApiModelProperty(value = "是否新品")
    private String isNew;
    @ApiModelProperty(value = "零售价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value = "最小单价")
    private BigDecimal minPrice;
    @ApiModelProperty(value = "最大单价")
    private BigDecimal maxPrice;
    @ApiModelProperty(value = "销售件数")
    private Integer saleNum;
    @ApiModelProperty(value = "库存数")
    private Integer stock;
    @ApiModelProperty(value = "描述")
    private String detail;
    @ApiModelProperty(value = "商品规格")
    private List<ShopGoodsAttr> goodsAttrs;
    @ApiModelProperty(value = "商品参数")
    private List<GoodsParams> goodsParams;
    @ApiModelProperty(value = "商品价格库存")
    private List<ShopGoodsSku> goodsSkus;

    @ApiModel(value = "GoodsParams对象", description = "商品参数")
    public static class GoodsParams {
        @ApiModelProperty(value = "参数名")
        private String paramName;
        @ApiModelProperty(value = "参数值")
        private String paramValue;
        public GoodsParams(String paramName, String paramValue) {
            this.paramName = paramName;
            this.paramValue = paramValue;
        }
    }
}
