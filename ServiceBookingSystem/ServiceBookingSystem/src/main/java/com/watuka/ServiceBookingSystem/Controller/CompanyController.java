package com.watuka.ServiceBookingSystem.Controller;

import com.watuka.ServiceBookingSystem.Dto.AdDTO;
import com.watuka.ServiceBookingSystem.Dto.ReservationDTO;
import com.watuka.ServiceBookingSystem.Repository.AdRepository;
import com.watuka.ServiceBookingSystem.Services.Company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AdRepository adRepository;

    @PostMapping("/ad/{userId}")
    public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
        String productCodeExists = String.valueOf(adRepository.findByServiceCode(adDTO.getServiceCode()));
        if (productCodeExists != null){
            boolean success = companyService.postAd(userId, adDTO);
            if (success){
                return ResponseEntity.status(HttpStatus.OK).build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }else {
            System.out.println("Service with serviceCode: "+adDTO.getServiceCode() + " Already Exists !!");
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    @GetMapping("/ads/{userId}")
    public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(companyService.getAllAds(userId));
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdById(@PathVariable Long adId){
        AdDTO adDTO = companyService.getAdById(adId);
        if (adDTO != null){
            return ResponseEntity.ok(adDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update/ad/{adId}")
    public ResponseEntity<?> updateAd(@PathVariable Long adId, @ModelAttribute AdDTO adDTO) throws IOException {
        boolean success = companyService.updateAd(adId, adDTO);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/ad/{adId}")
    public ResponseEntity<?> deleteAd(@PathVariable Long adId){
        boolean success = companyService.deleteAd(adId);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/bookings/{companyId}")
    public ResponseEntity<List<ReservationDTO>> getAllAdBookings(@PathVariable Long companyId){
        return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
    }

    @GetMapping("/booking/{bookingId}/{status}")
    public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status){
        boolean success = companyService.changeBookingStatus(bookingId, status);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
