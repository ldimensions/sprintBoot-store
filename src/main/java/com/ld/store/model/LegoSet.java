package com.ld.store.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "legosets")
public class LegoSet {
	
	@Id
	private String id;
    
	@TextIndexed
	private String name;
	private LegoSetDifficulty difficulty;
	
    @TextIndexed
	@Indexed(direction = IndexDirection.ASCENDING)
	private String theme;
	private Collection<ProductReview> reviews = new ArrayList<>();
	
    @Field("delivery")
    private DeliveryInfo deliveryInfo;
    
    @DBRef
    private PaymentOptions paymentOptions;
	
    public LegoSet(String name,
            String theme,
            LegoSetDifficulty difficulty,
            DeliveryInfo deliveryInfo,
            Collection<ProductReview> reviews,
            PaymentOptions paymentOptions){
		 this.name = name;
		 this.theme = theme;
		 this.difficulty = difficulty;
		 this.deliveryInfo = deliveryInfo;
		 this.paymentOptions = paymentOptions;
		 if(reviews != null){
		     this.reviews = reviews;
		 }
	}
	
	@Transient
	private int nbParts;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LegoSetDifficulty getDifficulty() {
		return difficulty;
	}

	public String getTheme() {
		return theme;
	}

	public Collection<ProductReview> getReviews() {
		return Collections.unmodifiableCollection(this.reviews);
	}

	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}

	public int getNbParts() {
		return nbParts;
	}
	
    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }
		
}
