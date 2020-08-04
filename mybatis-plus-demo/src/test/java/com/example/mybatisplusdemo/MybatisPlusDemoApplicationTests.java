package com.example.mybatisplusdemo;


import com.example.mybatisplusdemo.entity.UserTest;
import com.example.mybatisplusdemo.mapper.UserTestMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPlusDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MybatisPlusDemoApplicationTests {
    @Autowired
    private UserTestMapper userMapper;
    @Test
    void contextLoads() {
    }

    /**
     *  insert
     */
    @Test
    public void insertUser(){

        UserTest user = new UserTest();
        user.setId(6L);
        user.setName("张国荣");
        user.setAge(18);
        user.setEmail("zhangguorong@qq.com");

    int result = userMapper.insert(user);
        System.out.println(result); //影响的行数
        System.out.println(user); //id自动回填
}

    /**
     * update
     */
    @Test
    public void testUpdateById(){

        UserTest user = new UserTest();
        user.setId(1L);//id
        user.setAge(18);

        int result = userMapper.updateById(user);
        System.out.println(result);//影响的行数
        UserTest user1 = userMapper.selectById(1L);
        System.out.println(user1);

    }

    /**
     * 根据id查询记录
     */
    @Test
    public void testSelectById(){

        UserTest user = userMapper.selectById(1L);
        System.out.println(user);
    }

    /**
     * 通过多个id批量查询
     */
    @Test
    public void testSelectBatchIds(){

        //SELECT id,name,age,email FROM user_test WHERE id IN ( ? , ? , ? )
        List<UserTest> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * 简单的条件查询
     *   map中的key对应的是数据库中的列名。例如数据库user_id，实体类是userId，这时map的key需要填写user_id
     */
    @Test
    public void testSelectByMap(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张国荣");
        map.put("age", 18);
        // SELECT id,name,age,email FROM user_test WHERE name = ? AND age = ?
        List<UserTest> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 根据id删除记录
     */
    @Test
    public void testDeleteById(){

        int result = userMapper.deleteById(8L);
        System.out.println(result);
    }

    /**
     * 批量删除
     */
    @Test
    public void testDeleteBatchIds() {
        //DELETE FROM user_test WHERE id IN ( ? , ? , ? )
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }

    /**
     * 简单的条件查询删除
     *
     */
    @Test
    public void testDeleteByMap() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "张国荣");
        map.put("age", 18);

        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }
}
