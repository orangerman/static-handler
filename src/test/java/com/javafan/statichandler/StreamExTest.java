package com.javafan.statichandler;

import com.alibaba.fastjson2.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StreamExTest {

    @Test
    public void test() {
        List<User> users = new ArrayList<>();
        Role role1 = new Role(1, 1);
        Role role2 = new Role(1, 2);

        User user1 = new User();
        user1.setId(1);
        user1.setRole(role1);
        user1.setName("user1");
        users.add(user1);

        User user2 = new User();
        user2.setId(2);
        user2.setRole(role2);
        user2.setName("user2");
        users.add(user2);

        List<String> collect = users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
        log.info(JSON.toJSONString(collect));

        List<String> list = StreamEx.of(users).map(User::getName).toList();
        log.info(JSON.toJSONString(list));

        Map<Role, List<User>> role2users = StreamEx.of(users)
                .groupingBy(User::getRole);
        log.info(JSON.toJSONString(role2users));


        String joining = StreamEx.of(1, 2, 3)
                .joining(";");
        log.info(joining);

    }


}

@Data
class User {
    int id;
    String name;
    Role role = new Role();
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Role {
    int id;
    int type;
}