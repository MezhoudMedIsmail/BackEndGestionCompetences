package org.example.backendamine.Service.Impl;

import org.example.backendamine.Entities.Feedback;
import org.example.backendamine.Entities.TypeFeedback;
import org.example.backendamine.Repository.FeedbackRepository;
import org.example.backendamine.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        feedback.setNote(feedback.getNote());
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(long id, Feedback feedback) {
        Feedback existingFeedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        existingFeedback.setComment(feedback.getComment());
        existingFeedback.setNote(feedback.getNote());
        existingFeedback.setDate(feedback.getDate() == null ? new Date() : feedback.getDate());
        existingFeedback.setTitle_formation(feedback.getTitle_formation());
        return feedbackRepository.save(existingFeedback);
    }

    @Override
    public Feedback getFeedbackById(long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedback(long id) {
        feedbackRepository.deleteById(id);
    }
}
