package com.watuka.ServiceBookingSystem.Repository;

import com.watuka.ServiceBookingSystem.Entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    Ad findByServiceCode(String productCode);


    List<Ad> findAllByUserId(Long userId);

    List<Ad> findAllByServiceNameContaining(String name);
}
