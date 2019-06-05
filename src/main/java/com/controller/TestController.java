package com.controller;

import com.controller.vo.LoginVo;
import com.controller.vo.Menu;
import com.controller.vo.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TestController {

    private String token;

    private static List<User> userList = new ArrayList<User>();

    static {
        User user1 = new User();
        user1.setId(1);
        user1.setName("徐杰");
        user1.setMobile("13838383838");
        userList.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setName("张三");
        user2.setMobile("13333333333");
        userList.add(user2);
    }

    @RequestMapping("/react/demo/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody LoginVo loginVo) {
        if (loginVo.getUsername().equals("maohaiwen")
                && loginVo.getPassword().equals("123456")) {
            token = UUID.randomUUID().toString();
            return new HashMap<String, Object>() {{put("status", 200);put("token", token);}};
        }else {
            return new HashMap<String, Object>() {{put("status", 400);}};
        }
    }

    @RequestMapping("/react/demo/login/check")
    @ResponseBody
    public Map<String, Object> checkToken(@RequestParam("token") String token) {
        if (this.token.equals(token)) {
            return new HashMap<String, Object>() {{put("status", 200);}} ;
        }else {
            return new HashMap<String, Object>() {{put("status", 400);}} ;
        }
    }

    @RequestMapping("/react/demo/menus")
    @ResponseBody
    public Menu listMenus() {
        Menu root = new Menu();
        root.setName("root");
        root.setId(1);

        Menu r1 = new Menu();
        r1.setId(2);
        r1.setName("人员管理");

        Menu r1_1 = new Menu();
        r1_1.setId(3);
        r1_1.setName("员工管理");
        r1_1.setUrl("/user_list");

        Menu r1_2 = new Menu();
        r1_2.setId(4);
        r1_2.setName("权限管理");
        r1_2.setUrl("/access_list");

        r1.addChild(r1_1);
        r1.addChild(r1_2);

        root.addChild(r1);

        Menu r2 = new Menu();
        r2.setId(6);
        r2.setName("物资管理");

        Menu r2_1 = new Menu();
        r2_1.setId(7);
        r2_1.setName("物资管理");
        r2_1.setUrl("/goods_list");

        Menu r2_2 = new Menu();
        r2_2.setId(8);
        r2_2.setName("分发物资");
        r2_2.setUrl("/goods_send");

        r2.addChild(r2_1);
        r2.addChild(r2_2);
        root.addChild(r2);

        return root;
    }

    @RequestMapping("/react/demo/user_list")
    @ResponseBody
    public List<User> userList(String name, String mobile) {
        if (StringUtils.isEmpty(name)) {
            name = null;
        }
        if (StringUtils.isEmpty(mobile)) {
            mobile = null;
        }
        List<User> resultList = new ArrayList<User>();

        for (User user : userList) {
            if (name != null && mobile != null) {
                if (user.getName().equals(name)
                        && user.getMobile().equals(mobile)) {
                    resultList.add(user);
                }
            } else if (name != null) {
                if (user.getName().equals(name)) {
                    resultList.add(user);
                }
            } else if (mobile != null) {
                if (user.getMobile().equals(mobile)) {
                    resultList.add(user);
                }
            }else {
                return userList;
            }
        }

       return resultList;
    }

    @RequestMapping("/react/demo/user_add")
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody User user) {
        userList.add(user);
        return new HashMap<String, Object>() {{put("status", 200);}};
    }

    @RequestMapping("/react/demo/user_remove")
    @ResponseBody
    public Map<String, Object> removeUser(Integer userId) {
        Iterator<User> itr = userList.iterator();
        boolean find = false;
        while (itr.hasNext()) {
            User user = itr.next();
            if (user.getId().equals(userId)) {
                itr.remove();
                find = true;
            }
        }

        if (!find) {
            return new HashMap<String, Object>() {{put("status", 400);put("message", "找不到该用户！");}};
        }

        return new HashMap<String, Object>() {{put("status", 200);}};
    }

}
