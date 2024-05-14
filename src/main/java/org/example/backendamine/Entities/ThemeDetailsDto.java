package org.example.backendamine.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDetailsDto {
    Theme theme;
    Map<User, Map<Question, List<Reponse>>> userResponses = new HashMap<>();

    public ThemeDetailsDto(Theme theme) {
        this.theme = theme;
    }

    public void addUser(User user, Question question, Reponse reponse) {
        if (!userResponses.containsKey(user)) {
            userResponses.put(user, new HashMap<>());
        }
        userResponses.get(user).computeIfAbsent(question, k -> new ArrayList<>()).add(reponse);
    }
}
