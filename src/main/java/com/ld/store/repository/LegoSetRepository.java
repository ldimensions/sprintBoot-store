package com.ld.store.repository;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ld.store.model.LegoSet;
import com.ld.store.model.LegoSetDifficulty;

@Repository
public interface LegoSetRepository  extends MongoRepository<LegoSet, String>{

	Collection<LegoSet> findAllByThemeContains(String theme, Sort sort);
	Collection<LegoSet> findAllByDifficultyAndNameStartsWith(LegoSetDifficulty difficulty, String name);
	Collection<LegoSet> findAllBy(TextCriteria textCriteria);
	
	@Query("{'delivery.deliveryFee' : {$lt : ?0}}")
	Collection<LegoSet> FindAllByDeliveryPricesLessThan(int price);
	
    @Query("{'reviews.rating' : {$eq : 10}}")
    Collection<LegoSet> findAllByGreatReviews();
    
    @Query("{'paymentOptions.id' : ?0}")
    Collection<LegoSet> findByPaymentOptionId(String id);
}
 