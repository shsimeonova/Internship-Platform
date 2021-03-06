package org.isp.services.training_details_services;

import org.isp.domain.applications.training_details.TrainingCourseDto;
import org.isp.domain.applications.training_details.UserTrainingCourseDetails;
import org.isp.domain.applications.training_details.UserTrainingDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTrainingDetailsService {
    boolean createUserTrainingDetails(UserTrainingDetails userTrainingDetails,
                                      List<UserTrainingCourseDetails> utcdList,
                                      String username);

    @Transactional
    boolean createCourseDetailsForUser(List<UserTrainingCourseDetails> userTrainingCourseDetails, String username);

    List<TrainingCourseDto> getCourseDetailsForUsername(String username);

    double getAverageGradeForUsername(String usenname);

    void linkTrainingDetailsToUserApplication(String applicationUsername);

    boolean checkIfUserHasTrainingDetails(String username);
}
