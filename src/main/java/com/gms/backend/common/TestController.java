//package com.gms.backend.common;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class TestController {
//
//    @GetMapping("/test")
//    public String testApi() {
//        return "Backend is working successfully 🚀";
//    }
//}
package com.gms.backend.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db")
public class TestController {

    @Autowired
    private TestRepository repo;

    @PostMapping("/save")
    public TestEntity save() {
        TestEntity t = new TestEntity();
        t.setId(1L);
        t.setName("Dinesh");
        return repo.save(t);
    }

    @GetMapping("/all")
    public List<TestEntity> getAll() {
        return repo.findAll();
    }
}