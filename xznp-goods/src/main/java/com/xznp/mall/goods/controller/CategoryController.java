package com.xznp.mall.goods.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.Category;
import com.xznp.mall.goods.entity.CategoryVO;
import com.xznp.mall.goods.mapper.CategoryMapper;
import com.xznp.mall.util.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/goods/category")
public class CategoryController extends CurdController<Category> {


    @Resource
    CategoryMapper categoryMapper;


    /**
     * @return 所有商品分类的JSON字符串
     * @Author zyz
     * @Description 查询所有商品分类
     * @Date 09:39 2022/9/14
     * @Param
     **/
    @GetMapping("/listAll")
    public String selectAllCategoryList() {
        // List<CategoryVO> categoryVOS = iCategoryService.selectList();
        List<CategoryVO> categoryVOS = categoryMapper.selectCategoryList();
        return JSON.toJSONString(categoryVOS);
    }


    /**
     * @return
     * @Author zyz
     * @Description 列出第一层的分类
     * @Date 17:54 2022/9/26
     * @Param
     **/
    @GetMapping("/listLevelOne")
    public Result listLevelOne() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        //设置条件构造器，第一层的父ID为-4，并且过滤逻辑删除
        queryWrapper.eq("parent_id", "-4").eq("status", "E");
        //查询返回列表
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        if (categories.isEmpty()) {
            return Result.error().message("无目录！");
        }
        ArrayList categoryLevelOneList = new ArrayList<>();
        //遍历列表，将列表中的id和目录名添加到map
        for (int i = 0; i < categories.size(); i++) {
            Map<String, Object> categoryLevelOne = new HashMap<>();
            categoryLevelOne.put("value", categories.get(i).getId());
            categoryLevelOne.put("label", categories.get(i).getCategoryName());
            categoryLevelOneList.add(categoryLevelOne);
        }

        return Result.ok().data("categoryLevelOne", categoryLevelOneList);

    }

    /**
     * @return 分类的树形结构
     * @Author zyz
     * @Description 两次遍历返回二级分类的树形结构
     * @Date 14:50 2022/9/15
     * @Param
     **/
    @GetMapping("/listTree")
    public String listTree() {

        List<CategoryVO> categoryVOS = categoryMapper.selectCategoryList();

        //创建一级列表
        List categoryVOList = new ArrayList<>();

        for (int i = 0; i < categoryVOS.size(); i++) {
            //获取父id
            Long indexFatherID = categoryVOS.get(i).getParentId();
            //获取当前分类的id
            Long indexID = categoryVOS.get(i).getId();
            String categoryName = categoryVOS.get(i).getCategoryName();
            Map<String, Object> map = new HashMap<>();
            if (indexFatherID == -4) {
                //分类名称存入map
                map.put("label", categoryName);
                List childrenList = new ArrayList();
                for (int j = 0; j < categoryVOS.size(); j++) {
                    //父id与indexID比较，若相等存入map
                    if (Objects.equals(categoryVOS.get(j).getParentId(), indexID)) {
                        Map map1 = new HashMap<>();
                        map1.put("label", categoryVOS.get(j).getCategoryName());
                        childrenList.add(map1);
                    }
                }
                if (!childrenList.isEmpty()) {
                    map.put("children", childrenList);
                    //在一级分类有子分类时，添加分类id
                    //为了便于前端 element ui的 树形控件的展示，若需要所有的一级分类都带上id只需要移到if块外面

                }
                map.put("id", indexID);

            }
            if (!map.isEmpty()) {
                categoryVOList.add(map);
            }

        }


        return JSON.toJSONString(categoryVOList);
    }


    /**
     * @return affect rows
     * @Author zyz
     * @Description 逻辑删除一个商品分类
     * @Date 14:15 2022/9/14
     * @Param
     **/
    @PostMapping("/delCategoryById")
    public int deleteCategory(@RequestBody Category category) {
        category.setStatus("D");
        return categoryMapper.updateById(category);

    }


    /**
     * @Author zyz
     * @Description 分页查询商品分类
     * @Date 09:42 2022/9/14
     * @Param
     **/
    @PostMapping("/listCategoryByPage")
    public String listCategoryByPage(@RequestBody Map<String, Object> map) {


        return JSON.toJSONString(categoryMapper.selectPageCategoryList(extractPageFromRequestMap(map), extractWrapperFromRequestMap(map)));
    }


    /**
     * 从请求体中提取查询参数
     *
     * @param map
     * @return
     */
    private QueryWrapper<Category> extractWrapperFromRequestMap(Map<String, Object> map) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        for (String pageParam : pageParams) {
            map.remove(pageParam);
        }
        //queryWrapper.allEq(true,map, false);
        if (map.get("category_name") != null) {
            queryWrapper.like("category_name", map.get("category_name"));
        }
        if (map.get("parent_id") != null) {
            queryWrapper.eq("parent_id", map.get("parent_id"));
        } else {
            queryWrapper.eq("goods_category.level", 1);
        }
        queryWrapper.eq("goods_category.status", "E");//判断逻辑删除,以及筛选第一级的分类

        return queryWrapper;
    }

    private final String[] pageParams = {"size", "current", "orders"};

    /**
     * 从请求体中提取分页参数
     *
     * @param map
     * @return
     */
    private Page<Category> extractPageFromRequestMap(Map<String, Object> map) {

        Page<Category> page = new Page<>();

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

