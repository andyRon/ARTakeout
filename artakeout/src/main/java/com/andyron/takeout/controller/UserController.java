package com.andyron.takeout.controller;

import com.andyron.takeout.common.R;
import com.andyron.takeout.entity.User;
import com.andyron.takeout.service.UserService;
import com.andyron.takeout.utils.SMSUtils;
import com.andyron.takeout.utils.ValidateCodeUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author andyron
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("发送手机短信验证码")
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)) {
            // 生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            // 调用阿里云短信服务API完成短信发送
//            SMSUtils.sendMessage("artakeout", "", phone, code);

            session.setAttribute(phone, code);
            return R.success("手机验证码短信发送成功");
        }
        return R.error("手机验证码短信发送失败");
    }

    @ApiOperation("移动用户登录")
    @PostMapping("login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info(map.toString());
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
        // 从session中获取保存的验证码
        Object codeInSession = session.getAttribute(phone);

        if (codeInSession != null && codeInSession.equals(code)) {
            LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
            qw.eq(User::getPhone, phone);
            User user = userService.getOne(qw);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            // 在session中保存登录信息
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        return R.error("登录失败");
    }
}
