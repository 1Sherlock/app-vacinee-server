package uz.rootec.appvacineserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sherlock on 04.09.2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPatient {
    private UUID id;
    private String serial;

    private String vacinePlace;

    private String vacineType;

    private List<String> vacineSerials;

    private List<String> vacineDates;

    private String passportNumber;
    private String pinfl;

    private String fullName;

    private String birthDate;

    private String gender;

    private String givenDate;
}
