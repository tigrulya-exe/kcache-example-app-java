package io.spring.api;

import io.spring.application.TagsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.manasyan.kcache.core.annotations.KCacheable;

import java.util.HashMap;

@RestController
@RequestMapping(path = "tags")
public class TagsApi {
    private TagsQueryService tagsQueryService;

    @Autowired
    public TagsApi(TagsQueryService tagsQueryService) {
        this.tagsQueryService = tagsQueryService;
    }

    @GetMapping
    @KCacheable(tables = "tags")
    public ResponseEntity getTags() {
        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("tags", tagsQueryService.allTags());
        }});
    }
}
