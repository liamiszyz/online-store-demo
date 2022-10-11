package com.xznp.mall.goods.controller;


import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.Goods;
import com.xznp.mall.goods.mapper.GoodsMapper;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends CurdController<Goods> {


    GoodsMapper goodsMapper;


    /**
     * @return
     * @Author zyz
     * @Description 逻辑删除一个商品
     * @Date 09:54 2022/9/22
     * @Param
     **/
    @PostMapping("/delGoodsById")
    public Result delGoodsById(@RequestBody Goods goods) {
        goods.setStatus("D");
        int affectRows = goodsMapper.updateById(goods);
        if (affectRows > 0) {
            return Result.ok().message("删除成功！");
        } else {
            return Result.error().message("删除失败！");
        }
    }
}
