package com.ld.store.web;


import java.util.Collection;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.ld.store.model.LegoSet;
import com.ld.store.model.LegoSetDifficulty;
import com.ld.store.repository.LegoSetRepository;

@RestController
@RequestMapping("/store/api")
public class StoreController {

	private LegoSetRepository legoSetRepository;

	public StoreController(LegoSetRepository legoSetRepository) {
		this.legoSetRepository = legoSetRepository;
	}
	
	@PostMapping
	public void insert(@RequestBody LegoSet legoSet) {
		this.legoSetRepository.insert(legoSet);
	}
	
	@PutMapping
	public void update(@RequestBody LegoSet legoSet) {
		this.legoSetRepository.save(legoSet);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		this.legoSetRepository.deleteById(id);
	}
	
	@GetMapping("/all")
	public Collection<LegoSet> all(){
		Sort sortByThemeAsc = Sort.by("theme").ascending();
		Collection<LegoSet> legoSet = this.legoSetRepository.findAll();
		return legoSet;
	}
	
	@GetMapping("/{id}")
	public LegoSet findById(@PathVariable String id) {
		LegoSet legoSet = this.legoSetRepository.findById(id).orElse(null);
		return legoSet;
	}
	
    @GetMapping("/byTheme/{theme}")
    public Collection<LegoSet> byTheme(@PathVariable String theme){
        Sort sortByThemeAsc = Sort.by("theme").ascending();
        return this.legoSetRepository.findAllByThemeContains(theme, sortByThemeAsc);
    }
	
	@GetMapping("/filterDifficultyAndName")
	public Collection<LegoSet> filterDifficultyAndName() {
		return this.legoSetRepository.findAllByDifficultyAndNameStartsWith(LegoSetDifficulty.HARD, "M");
	}	
	
	@GetMapping("/byDeliveryPricesLessThan/{price}")
	public Collection<LegoSet> byDeliveryPricesLessThan(@PathVariable int price) {
		return this.legoSetRepository.FindAllByDeliveryPricesLessThan(price);
	}
	
    @GetMapping("greatReviews")
    public Collection<LegoSet> byGreatReviews(){
        return this.legoSetRepository.findAllByGreatReviews();
    }
    
    @GetMapping("fullTextSearch/{text}")
    public Collection<LegoSet> fullTextSearch(@PathVariable String text){
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);
        return this.legoSetRepository.findAllBy(textCriteria);
    }
    
//    @GetMapping("bestBuys")
//    public Collection<LegoSet> bestBuys(){
//        // build query
//        QLegoSet query = new QLegoSet("query");
//        BooleanExpression inStockFilter =  query.deliveryInfo.inStock.isTrue();
//        Predicate smallDeliveryFeeFilter =  query.deliveryInfo.deliveryFee.lt(50);
//        Predicate hasGreatReviews =  query.reviews.any().rating.eq(10);
//
//        Predicate bestBuysFilter = inStockFilter
//                .and(smallDeliveryFeeFilter)
//                .and(hasGreatReviews);
//
//        // pass the query to findAll()
//        return (Collection<LegoSet>) this.legoSetRepository.findAll(bestBuysFilter);
//    }
	
//	private MongoTemplate mongoTemplate;
//	
//	public StoreController(MongoTemplate mongoTemplate) {
//		this.mongoTemplate = mongoTemplate;
//	}
//	
//	@PostMapping
//	public void insert(@RequestBody LegoSet legoSet) {
//		this.mongoTemplate.insert(legoSet);
//	}
//	
//	@PutMapping
//	public void update(@RequestBody LegoSet legoSet) {
//		this.mongoTemplate.save(legoSet);
//	}
//	
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable String id) {
//		this.mongoTemplate.remove(new Query(Criteria.where("id").is(id)), LegoSet.class);
//	}
//	
//	@GetMapping("/all")
//	public Collection<LegoSet> all(){
//		Collection<LegoSet> legoSet = this.mongoTemplate.findAll(LegoSet.class);
//		return legoSet;
//	}
}
