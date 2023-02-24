package com.example.cactus.dto;

import com.example.cactus.entity.Leaf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeafRequest {
    String code;

    // TODO: directAccept
    boolean directAccept = Boolean.FALSE;

    public Leaf toEntity(){
        return new Leaf(code);
    }
}
