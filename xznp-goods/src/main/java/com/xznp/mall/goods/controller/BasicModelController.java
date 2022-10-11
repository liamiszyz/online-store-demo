package com.xznp.mall.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.BaiscModelVO;
import com.xznp.mall.goods.entity.BasicModel;
import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryVO;
import com.xznp.mall.goods.mapper.BasicModelMapper;
import com.xznp.mall.util.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-27
 */
@RestController
@RequestMapping("/goods/model")
public class BasicModelController extends CurdController {


    @Resource
    BasicModelMapper basicModelMapper;


    /**
     * @return com.xznp.mall.util.result.Result
     * @Author zyz
     * @Description 获取所有型号列表
     * @Date 13:59 2022/9/27
     * @Param []
     **/
    @GetMapping("/getAllModelList")
    public Result getAllModelList() {

        List<BaiscModelVO> baiscModelVOS = basicModelMapper.selectAllBasicModel();
        return Result.ok().data("modelList", baiscModelVOS);
    }


    /**
     * @return
     * @Author zyz
     * @Description 逻辑删除一个型号
     * @Date 15:21 2022/9/27
     * @Param
     **/

    @PostMapping("/del")
    public Result deleteAModel(@RequestBody BasicModel basicModel) {
        basicModel.setStatus("D");
        int affectedRows = basicModelMapper.updateById(basicModel);
        if (affectedRows > 0) {
            return Result.ok().message("插入成功！");
        } else {
            return Result.error().message("插入失败！");
        }

    }


    /**
     * @return com.xznp.mall.util.result.Result
     * @Author zyz
     * @Description 分页获取所有型号列表
     * @Date 14:00 2022/9/27
     * @Param [map]
     **/
    @PostMapping("/getModelList")
    public Result getModelListByPage(@RequestBody Map<String, Object> map) {


        IPage<BaiscModelVO> categoryVOIPage = basicModelMapper.selectPageModelList(extractPageFromRequestMap(map), extractWrapperFromRequestMap(map));
        return Result.ok().data("modelList", categoryVOIPage);
    }

    /**
     * 从请求体中提取查询参数
     *
     * @param map
     * @return
     */
    private QueryWrapper<BaiscModelVO> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<BaiscModelVO> queryWrapper = new QueryWrapper<>();
        for (String pageParam : pageParams) {
            map.remove(pageParam);
        }
        //queryWrapper.allEq(true,map, false);

        queryWrapper.eq("basic_model.status", "E");

        return queryWrapper;
    }

    private final String[] pageParams = {"size", "current", "orders"};

    /**
     * 从请求体中提取分页参数
     *
     * @param map
     * @return
     */
    private Page<BaiscModelVO> extractPageFromRequestMap(Map<String, Object> map) {

        Page<BaiscModelVO> page = new Page<>();

        String key = pageParams[0];
        if (map.containsKey(key) && map.get(key) instanceof Integer) {
            page.setSize((Integer) map.get(key));
        }

        key = pageParams[1];
        if (map.containsKey(key) && map.get(key) instanceof Integer) {
            page.setCurrent((Integer) map.get(key));
        }

        // 排序
        key = pageParams[2];
        if (map.containsKey(key) && map.get(key) instanceof List) {
            List<OrderItem> orderItemList = new ArrayList<>();
            for (String orderArrStr : (List<String>) map.get(key)) {
                if (StringUtils.isBlank(orderArrStr) || !orderArrStr.contains(" ")) {
                    continue;
                }
                String[] orderArr = orderArrStr.split(" ");
                if ("desc".equals(orderArr[1])) {
                    orderItemList.add(OrderItem.desc(orderArr[0]));
                } else {
                    orderItemList.add(OrderItem.asc(orderArr[0]));
                }
            }
            page.setOrders(orderItemList);
        }

        return page;
    }

}
