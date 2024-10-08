package com.watuka.ServiceBookingSystem.Dto;

import com.watuka.ServiceBookingSystem.Enums.ReservationStatus;
import com.watuka.ServiceBookingSystem.Enums.ReviewStatus;

import lombok.Data;

import java.util.Date;
@Data
public class ReservationDTO {

    private Long id;

    private String serviceName;

    private ReservationStatus reservationStatus;

    private ReviewStatus reviewStatus;

    private Date bookDate;

    private Long userId;

    private String userName;

    private Long companyId;

    private Long adId;
}
