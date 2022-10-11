package com.xznp.mall.goods.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryRela;
import com.xznp.mall.goods.mapper.CategoryRelaMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类目归属表 控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/goods/category-rela")
public class CategoryRelaController extends CurdController<CategoryRela> {


    @Resource
    CategoryRelaMapper categoryRelaMapper;

    /**
     * @return ['乡总农品', '乡总优选']
     * @Author zyz
     * @Description     通过id获取 relaList
     * @Date 15:25 2022/9/16
     * @Param  category
     **/
    @PostMapping("/getRelaList")
    public String listCategoryRelaByCategoryID(@RequestBody Category category) {


        QueryWrapper<CategoryRela> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", category.getId());
        List<CategoryRela> categoryRelas = categoryRelaMapper.selectList(queryWrapper);

        List<String> relaValList = new ArrayList<>();
        for (int i = 0; i < categoryRelas.size(); i++) {
            if (categoryRelas.get(i).getRelaVal().equals("XZNP")) {
                relaValList.add("乡总农品");
            }
            if (categoryRelas.get(i).getRelaVal().equals("XZYX")) {
                relaValList.add("乡总优选");
            }
        }
        return JSON.toJSONString(relaValList);


    }

    /**
     * @return
     * @Author zyz
     * @Description 根据传入的列表 ['乡总农品', '乡总优选']  来修改对应的 category-rela
     * 没写完
     * map:
     * {
     * "relaList":[],
     * "id":categoryID
     * }
     * <p>
     * 通过CategoryID 找到相应的 category-rela，一般有1-3条记录（有些是会重复）
     * 传入的relaList 最多只有两个
     * <p>
     * 1.删除所有的对应 category-rela，再全部插入（逻辑删除 会导致频繁修改时 条目增加过多？ 但是考虑到对类目的修改会比较少，可行？）
     * <p>
     * <p>
     * 2.遍历查到的列表，若 relavalue 存在，不变，不存在就添加
     * <p>
     * 暂定第二种
     * @Date 17:08 2022/9/16
     * @Param
     **/

    @PostMapping("/updateCategoryRela")
    public int updateCategoryRela(@RequestBody Map<String, Object> map) {
//        if (map.get("relaList") != null) {
//            List<String> relaList = (List<String>) map.get("relaList");
//            for (int i = 0; i < relaList.size(); i++) {
//                if (relaList.get(i).equals("乡总农品")) {
//                    CategoryRela categoryRela = new CategoryRela();
//
//                    categoryRela.setCategoryId((Integer) map.get("id"));
//                    categoryRela.setRelaVal("XZNP");
//                }
//                if (relaList.get(i).equals("乡总优选")) {
//                    return 0;
//                }
//
//            }
//        }
//        return 0;

        if (map.get("id") != null) {
            QueryWrapper<CategoryRela> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("category_id", map.get("id"));
            List<CategoryRela> categoryRelas = categoryRelaMapper.selectList(queryWrapper);

            ArrayList<String> relaList = (ArrayList<String>) map.get("relaList");

            //用来标记传回的选择
            boolean isXZNPChosen = false;
            boolean isXZYXChosen = false;

            for (int i = 0; i < relaList.size(); i++) {
                if (relaList.get(i).equals("乡总农品")) {
                    isXZNPChosen = true;
                }
                if (relaList.get(i).equals("乡总优选")) {
                    isXZYXChosen = true;
                }
            }


            //用来标记数据库中的选择
            boolean isXZNP = false;
            boolean isXZYX = false;
            for (int i = 0; i < categoryRelas.size(); i++) {
                if (categoryRelas.get(i).getRelaVal().equals("XZNP")) {
                    isXZNP = true;
                }
                if (categoryRelas.get(i).getRelaVal().equals("XZYX")) {
                    isXZYX = true;
                }
            }
            if (isXZNP == isXZNPChosen) {
                //啥也不干
            } else {
                if (isXZNPChosen == true) {
                    //添加一条
                }

            }
            if (isXZYX == isXZYXChosen) {
                //啥也不干
            } else {
                if (isXZYXChosen == true) {
                    //添加一条
                }

            }
        }
        //还没写完
        return 0;
    }

}