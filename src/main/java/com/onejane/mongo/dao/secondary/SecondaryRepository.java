package com.onejane.mongo.dao.secondary;

import com.onejane.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondaryRepository extends MongoRepository<User, String> {
}