package com.folderManager.rep;

import com.folderManager.model.Node;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileRepo extends MongoRepository<Node, Integer> {

    List<Node> findByParent(int id);
    Node findById(int id);
}
