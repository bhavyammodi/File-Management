package com.folderManager.services;

import com.folderManager.model.Node;
import com.folderManager.rep.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

@Service
public class FileService {
    final private FileRepo fileRepo;

    public FileService(FileRepo fileRepo, MongoTemplate mongoTemplate) {
        this.fileRepo = fileRepo;
        this.mongoTemplate = mongoTemplate;
    }

    public Node saveFiles(Node st) {
        return fileRepo.save(st);
    }

    public List<Node> getAllFiles() {
        return fileRepo.findAll();
    }
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Node> findNodesByParent(int parentValue) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("parent").is(parentValue))
        );
        return mongoTemplate.aggregate(aggregation, "Folders", Node.class).getMappedResults();

    }
    public void deleteAllChild(int id, boolean soft) {
//      Bulk
        Queue<Integer> toDelete = new LinkedList<>();
        toDelete.add(id);

        while (toDelete.size() > 0) {
            int v = toDelete.remove();
            List<Node> lst = findNodesByParent(v);
            for (Node x : lst)
                toDelete.add(x.getId());
            if (soft == true) {
                Node x = this.fileRepo.findById(v);
                x.setSoftDelete(true);
                saveFiles(x);
            } else
                fileRepo.deleteById(v);
        }
    }
}