package org.example.backendamine.Entities.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendamine.Entities.Reponse;
import org.example.backendamine.Repository.QuestionRepository;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReponseRequest {
    long idQuestion;
    String reponse;
}
