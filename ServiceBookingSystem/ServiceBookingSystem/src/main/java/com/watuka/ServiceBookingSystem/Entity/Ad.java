package com.watuka.ServiceBookingSystem.Entity;

import com.watuka.ServiceBookingSystem.Dto.AdDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    private String serviceCode;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public AdDTO getAdDto(){
        AdDTO adDTO = new AdDTO();

        adDTO.setId(id);
        adDTO.setServiceName(serviceName);
        adDTO.setServiceCode(serviceCode);
        adDTO.setDescription(description);
        adDTO.setPrice(price);
        adDTO.setCompanyName(user.getName());
        adDTO.setReturnedImg(img);

        return adDTO;
    }
}
