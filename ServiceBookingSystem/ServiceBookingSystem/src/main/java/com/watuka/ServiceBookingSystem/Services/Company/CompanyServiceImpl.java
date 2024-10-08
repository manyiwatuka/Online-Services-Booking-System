package com.watuka.ServiceBookingSystem.Services.Company;

import com.watuka.ServiceBookingSystem.Dto.AdDTO;
import com.watuka.ServiceBookingSystem.Dto.ReservationDTO;
import com.watuka.ServiceBookingSystem.Entity.Ad;
import com.watuka.ServiceBookingSystem.Entity.Reservation;
import com.watuka.ServiceBookingSystem.Entity.User;
import com.watuka.ServiceBookingSystem.Enums.ReservationStatus;
import com.watuka.ServiceBookingSystem.Repository.AdRepository;
import com.watuka.ServiceBookingSystem.Repository.ReservationRepository;
import com.watuka.ServiceBookingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public boolean postAd(Long userId, AdDTO adDTO) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()){
            Ad ad = new Ad();
            ad.setServiceName(adDTO.getServiceName());
            ad.setServiceCode(adDTO.getServiceCode());
            ad.setDescription(adDTO.getDescription());
            ad.setImg(adDTO.getImg().getBytes());
            ad.setPrice(adDTO.getPrice());
            ad.setUser(optionalUser.get());

            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public List<AdDTO> getAllAds(Long userId){
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toUnmodifiableList());
    }

    public AdDTO getAdById(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()){
            return optionalAd.get().getAdDto();
        }
        return null;
    }

    public boolean updateAd(Long adId, AdDTO adDTO) throws IOException {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()){
            Ad ad = optionalAd.get();

            ad.setServiceName(adDTO.getServiceName());
            ad.setServiceCode(adDTO.getServiceCode());
            ad.setDescription(adDTO.getDescription());
            ad.setPrice(adDTO.getPrice());

            if (adDTO.getImg() != null){
                ad.setImg(adDTO.getImg().getBytes());
            }
            adRepository.save(ad);
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteAd(Long adId){
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isPresent()){
            adRepository.delete(optionalAd.get());
            return true;
        }
        return false;
    }

    public List<ReservationDTO> getAllAdBookings(Long companyId){
        return reservationRepository.findAllByCompanyId(companyId)
                .stream().map(Reservation::getReservationDTO).collect(Collectors.toUnmodifiableList());
    }

    public boolean changeBookingStatus(Long bookingId, String status){
        Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
        if (optionalReservation.isPresent()){
            Reservation existingReservation = optionalReservation.get();
            if (Objects.equals(status, "Approve")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else {
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(existingReservation);
            return true;
        }
        return  false;
    }
}
