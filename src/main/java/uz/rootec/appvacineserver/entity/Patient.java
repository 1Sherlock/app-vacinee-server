package uz.rootec.appvacineserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.rootec.appvacineserver.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Sherlock on 04.09.2021.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends AbstractEntity {
    private String serial;

    @Column(columnDefinition = "TEXT")
    private String vacinePlace;

    private String vacineType;

    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<String> vacineSerials;

    @ElementCollection
    @Column(columnDefinition = "TEXT")
        private List<String> vacineDates;

    private String passportNumber;
    private String pinfl;

    private String fullName;

    private String birthDate;

    private String gender;

    private String givenDate;
}
