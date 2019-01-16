package com.yash.microservices.feedback;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final FeedbackRepository repository;
    private final ModelMapper modelMapper;
    private final FeedbackResourceAssembler assembler;

    public FeedbackService(final FeedbackRepository repository,
                           final ModelMapper modelMapper,
                           final FeedbackResourceAssembler assembler) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.assembler = assembler;
    }

    public FeedbackResource getFeedbackById(Long id) {
        return assembler.toResource(repository.findById(id).orElseThrow(
                () -> new FeedbackNotFoundException("feedback with id " + id + " not found")));
    } 


    public Resources<FeedbackResource> getAllFeedback() {
        return new Resources<>(assembler.toResources(repository.findAllByOrderByIdDesc()));
    }

    public FeedbackResource createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);
        Feedback savedFeedback = repository.save(feedback);
        return assembler.toResource(savedFeedback);
    }
}
