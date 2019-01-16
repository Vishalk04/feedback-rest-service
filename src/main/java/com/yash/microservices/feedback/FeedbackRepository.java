package com.yash.microservices.feedback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class FeedbackRepository {
	
	private long id = 1;
	Map<Long, Feedback> feedbackData = new HashMap<>();

	public Optional<Feedback> findById(Long id) {
		return Optional.of(feedbackData.get(id));
	}

	public List<Feedback> findAllByOrderByIdDesc() {
		List<Feedback> feedback = new ArrayList<>(feedbackData.values());
		return feedback;
	}

	public Feedback save(Feedback feedback) {
		feedback.setId(id++);
		feedbackData.put(feedback.getId(), feedback);
		return feedback;
	}

}
