package com.yml.mySpring.conteoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}