package com.xznp.mall.staff.controller;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xznp.mall.controller.CurdController;
import com.xznp.mall.staff.entity.TsStaffInfo;
import com.xznp.mall.staff.mapper.TsStaffInfoMapper;
import com.xznp.mall.util.JWTUtils;
import com.xznp.mall.util.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 人员表 控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
 */
@RestController
@RequestMapping("/staff")
public class TsStaffInfoController extends CurdController<TsStaffInfo> {


    @Resource
    TsStaffInfoMapper tsStaffInfoMapper;


    /**
     * @return
     * @Author zyz
     * @Description //后台管理简单验证 使用JWT
     * @Date 17:20 2022/9/15
     * @Param
     **/
    @PostMapping("/doPlatLogin")
    public Result doPlatLogin(@RequestBody TsStaffInfo tsStaffInfo) throws UnsupportedEncodingException {


        QueryWrapper<TsStaffInfo> queryWrapper = new QueryWrapper<>();
        //通过账号密码查询用户是否存在
        queryWrapper.eq("staff_code", tsStaffInfo.getStaffCode()).eq("passwd", tsStaffInfo.getPasswd());
        TsStaffInfo staffInfo = tsStaffInfoMapper.selectOne(queryWrapper);

        //通过返回信息判断用户是否存在
        if (staffInfo == null) {
            return Result.error().message("账号或者密码错误！");
        }
        //将返回的用户信息放在map中
        Map<String, Object> map = new HashMap<>();
        map.put("staffInfo", staffInfo);

        //取出用户的id与用户名 ID用来做唯一标识用户，用户名用来显示
        Map<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("staffCode", staffInfo.getStaffCode());
        userInfoMap.put("id", staffInfo.getId().toString());
        //生成token
        String token = JWTUtils.createToken(userInfoMap);
        //将token存入map
        map.put("token", token);

        //用户信息返回
        return Result.ok().data("staffInfo", staffInfo).data("token", token);

    }

    /**
     * @return boolean
     * @Author zyz
     * @Description 检查token是否有效
     * @Date 17:59 2022/9/19
     * @Param [request]
     **/
    @GetMapping("/checkToken")
    public boolean checkToken(HttpServletRequest request) {
        //前端在header中传入一个token，这里在header中取出
        String token = request.getHeader("token");
        //验证token是否合法，如果不合法会抛出异常
        try {
            DecodedJWT verify = JWTUtils.verify(token);
        } catch (Exception e) {
            //接收异常直接返回false
            return false;
        }
        //合法返回true
        return true;
    }

    /**
     * @return java.lang.String
     * @Author zyz
     * @Description 列出所有staff
     * @Date 10:11 2022/9/30
     * @Param []
     **/
    @GetMapping("/listAllStaff")
    public String listAllStaff() {

        QueryWrapper<TsStaffInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", "E");//过滤逻辑删除

        return JSON.toJSONString(tsStaffInfoMapper.selectList(queryWrapper));

    }


    /**
     * @return com.xznp.mall.util.result.Result
     * @Author zyz
     * @Description 更新密码 与更新信息一样主要区别在于前端调用接口的参数不同
     * @Date 10:11 2022/9/30
     * @Param [tsStaffInfo]
     **/
    @PostMapping("/updatePasswordById")
    public Result updatePasswordById(@RequestBody TsStaffInfo tsStaffInfo) {

        int update = tsStaffInfoMapper.updateById(tsStaffInfo);
        //判断是否修改成功
        if (update > 0) {
            return Result.ok().message("修改成功！");
        } else {
            return Result.error().message("修改失败！");
        }
    }


    /**
     * @return com.xznp.mall.util.result.Result
     * @Author zyz
     * @Description 更新信息
     * @Date 10:13 2022/9/30
     * @Param [tsStaffInfo]
     **/
    @PostMapping("/updateInfoById")
    public Result updateInfoById(@RequestBody TsStaffInfo tsStaffInfo) {

        int update = tsStaffInfoMapper.updateById(tsStaffInfo);
        if (update > 0) {
            return Result.ok().message("修改成功！");
        } else {
            return Result.error().message("修改失败！");
        }
    }


}