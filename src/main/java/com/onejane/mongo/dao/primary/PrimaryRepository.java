package com.onejane.mongo.dao.primary;

import com.onejane.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrimaryRepository extends MongoRepository<User, String> {
}