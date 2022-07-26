package com.yml.mySpring.conteoller;

import com.yml.mySpring.utils.VerifyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;

@Controller
public class IndexController {
    @RequestMapping("spring/say")
    @ResponseBody
    public  String say(){
        return "springboot is coming";
    }

    /**
     * This is test for springboot pg，return a HASHMAP extend MAP
     * @return
     */
    @RequestMapping("user/say2")
    @ResponseBody
    public HashMap<Object, Object> say2(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("yml","kjl");
        return objectObjectHashMap;
    }
    @RequestMapping("/user/error")
    @ResponseBody
    public HashMap<Object, Object> error(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("yml","error");
        return objectObjectHashMap;
    }

    @PostMapping("/getcode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 获取到session
        HttpSession session = request.getSession();
        // 取到sessionid
        String id = session.getId();

        // 利用图片工具生成图片
        // 返回的数组第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.newBuilder()
                .setWidth(120)   //设置图片的宽度
                .setHeight(35)   //设置图片的高度
                .setSize(4)      //设置字符的个数
                .setLines(5)    //设置干扰线的条数
                .setFontSize(25) //设置字体的大小
                .setTilt(true)   //设置是否需要倾斜
                .setBackgroundColor(Color.LIGHT_GRAY) //设置验证码的背景颜色
                .build()         //构建VerifyUtil项目
                .createImage();  //生成图片
        // 将验证码存入Session
        session.setAttribute("SESSION_VERIFY_CODE_" + id, objs[0]);
        // 打印验证码
        System.out.println(objs[0]);

        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
    @PostMapping("user/checkcode")
    @ResponseBody
    public String checkCode(String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();

        // 将redis中的尝试次数减一
        String verifyCodeKey = "VERIFY_CODE_" + id;
        //long num = redisTemplate.opsForValue().decrement(verifyCodeKey);

        // 如果次数次数小于0 说明验证码已经失效
        /*if (num < 0) {
            return "验证码失效!";
        }*/

        // 将session中的取出对应session id生成的验证码
        String serverCode = (String) session.getAttribute("SESSION_VERIFY_CODE_" + id);
        // 校验验证码
        if (null == serverCode || null == code || !serverCode.toUpperCase().equals(code.toUpperCase())) {
            return "验证码错误!";
        }

        // 验证通过之后手动将验证码失效
      //  redisTemplate.delete(verifyCodeKey);

        // 这里做具体业务相关

        return "验证码正确!";
    }
    @PostMapping("user/as")
    @ResponseBody
    public String as(String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();

        // 将redis中的尝试次数减一
        String verifyCodeKey = "VERIFY_CODE_" + id;
        //long num = redisTemplate.opsForValue().decrement(verifyCodeKey);

        // 如果次数次数小于0 说明验证码已经失效
        /*if (num < 0) {
            return "验证码失效!";
        }*/

        // 将session中的取出对应session id生成的验证码
        String serverCode = (String) session.getAttribute("SESSION_VERIFY_CODE_" + id);
        // 校验验证码
        if (null == serverCode || null == code || !serverCode.toUpperCase().equals(code.toUpperCase())) {
            return "验证码错误!";
        }

        // 验证通过之后手动将验证码失效
        //  redisTemplate.delete(verifyCodeKey);

        // 这里做具体业务相关

        return "验证码正确!";
    }

}