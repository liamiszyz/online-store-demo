package com.xznp.mall.goods.controller;


import com.xznp.mall.controller.CurdController;
import com.xznp.mall.goods.entity.Channel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  控制器
 * </p>
 *
 * @author zyz
 * @since 2022-09-13
*/
@RestController
@RequestMapping("/goods/channel")
public class ChannelController extends CurdController<Channel> {

}
