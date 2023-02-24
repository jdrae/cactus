package com.example.cactus;

import com.example.cactus.dto.StatusRequest;
import com.example.cactus.dto.LeafRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/entity")
@RestController
@RequiredArgsConstructor
public class LeafController {
    private final LeafService leafService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody LeafRequest dto){
        leafService.create(dto);
    }

    @PostMapping("/{stemId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long stemId, @RequestBody LeafRequest dto){
        leafService.update(stemId, dto);
    }



    @PutMapping("/logs/{leafId}")
    @ResponseStatus(HttpStatus.OK)
    public void confirm(@PathVariable Long leafId, @RequestBody StatusRequest dto){
        leafService.confirm(leafId, dto);
    }

    @DeleteMapping("/logs/{leafId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSelf(@PathVariable Long leafId){
        leafService.deleteSelf(leafId);
    }
}
