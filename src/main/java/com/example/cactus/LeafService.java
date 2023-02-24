package com.example.cactus;

import com.example.cactus.dto.StatusRequest;
import com.example.cactus.dto.LeafRequest;
import com.example.cactus.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LeafService{

    private final StemRepository stemRepository;
    private final LeafRepository leafRepository;

    public void create(LeafRequest dto) {
        Leaf leaf = dto.toEntity();
        leafRepository.save(leaf);
    }

    public void update(Long stemId, LeafRequest dto){
        Stem stem = getStem(stemId); // 없으면 accepted 안된것임
        Leaf oldLeaf = stem.getLeaf();

        Leaf leaf = dto.toEntity();
        leaf.setStemId(stemId);
        assert leaf.getStemId() != null;

        copy(leaf, oldLeaf);

        leafRepository.save(leaf);
    }

    public void delete(Long stemId){

    }

    public void confirm(Long leafId, StatusRequest dto){
        Leaf leaf = getLeaf(leafId);
        if(leaf.getStatus() != Status.PENDING)
            throw new RuntimeException("CUSTOM ERR: status is not pending");

        leaf.setStatus(dto.getStatus());

        if(leaf.getStatus() == Status.ACCEPTED){
            if(leaf.getStemId() == null){ // create 해야
                Stem stem = new Stem();
                stem.setLeaf(leaf);
                stemRepository.save(stem);
                leaf.setStemId(stem.getId());
            }
            else{ // update 해야
                Stem stem = getStem(leaf.getStemId());
                stem.setLeaf(leaf);
                stemRepository.save(stem);
                // 마지막 업뎃 ~ 현재 업뎃 사이의 모든 PENDING -> REJECTED
                List<Leaf> pendingList = leafRepository.findByStemIdAndStatus(stem.getId(), Status.PENDING);
                for (Leaf pending : pendingList) {
                    pending.setStatus(Status.REJECTED);
                }
                leafRepository.saveAll(pendingList);
            }
        }
        leafRepository.save(leaf);
    }

    public void deleteSelf(Long leafId){
        Leaf entity = getLeaf(leafId);
        if(entity.getStatus() != Status.PENDING)
            throw new RuntimeException("CUSTOM ERR: status is not pending");
        leafRepository.delete(entity);
    }

    // helper methods
    private Leaf getLeaf(Long id){
        return leafRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CUSTOM ERR: data not found."));
    }
    private Stem getStem(Long id){
        return stemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CUSTOM ERR: data not found."));
    }

    public void copy(Leaf dst, Leaf src){
        assert dst != null && src != null;

        if(dst.getCode() == null && src.getCode() != null)
            dst.setCode(src.getCode());
    }
}