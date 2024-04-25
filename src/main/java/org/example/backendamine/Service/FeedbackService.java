package org.example.backendamine.Service;

import org.example.backendamine.Entities.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback updateFeedback(long id, Feedback feedback);
    Feedback getFeedbackById(long id);
    List<Feedback> getAllFeedback();
    void deleteFeedback(long id);
}
