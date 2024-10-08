package com.watuka.ServiceBookingSystem.Services.Client;

import com.watuka.ServiceBookingSystem.Dto.AdDTO;
import com.watuka.ServiceBookingSystem.Dto.AdDetailsForClientDTO;
import com.watuka.ServiceBookingSystem.Dto.ReservationDTO;
import com.watuka.ServiceBookingSystem.Dto.ReviewDTO;

import java.util.List;

public interface ClientService {

    List<AdDTO> getAllAds();

    List<AdDTO> searchAdByName(String name);

    boolean bookService(ReservationDTO reservationDTO);

    AdDetailsForClientDTO getAdDetailsByAdId (Long adId);

    List<ReservationDTO> getAllBookingsByUserId(Long userId);

    Boolean giveReview(ReviewDTO reviewDTO);
}
