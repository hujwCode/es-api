package com.example.esjd.controller;

import com.example.esjd.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : hujiawei
 * @version : 1.0.0
 * @date : 2020/12/7 9:46 下午
 */
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/param/{keyword}")
    public boolean parse(@PathVariable("keyword") String keyword) throws Exception {
        return contentService.parseContent(keyword);
    }

    @GetMapping("/search/{pageNo}/{pageSize}/{keyword}")
    public List<Map<String, Object>> searchPage(@PathVariable("keyword") String keyword
                                                , @PathVariable("pageNo") int pageNo
                                                , @PathVariable("pageSize") int pageSize) throws Exception {
        return contentService.searchPage(keyword, pageNo, pageSize);
    }
}
