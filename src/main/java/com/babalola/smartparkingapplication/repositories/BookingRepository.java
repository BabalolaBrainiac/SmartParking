package com.babalola.smartparkingapplication.repositories;

import com.babalola.smartparkingapplication.domain.entities.Booking;
import com.babalola.smartparkingapplication.domain.entities.User;
import com.babalola.smartparkingapplication.domain.enums.BookingStatus;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
   List<Booking> findByUser(User user);


   @Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.status = :status")
   List<Booking> findByUserAndBookingStatus(@Param("userId") Long userId, @Param("status") BookingStatus status);
   @Query("SELECT b FROM Booking b WHERE b.availableParkingSpace.parkingGarage.parkOwner.id = :parkOwnerId")
   List<Booking> findAllByParkOwnerId(@Param("parkOwnerId") Long parkOwnerId);

   @Query("SELECT b FROM Booking b WHERE b.availableParkingSpace.parkingGarage.parkOwner.id = :parkOwnerId AND b.status = :status")
   List<Booking> findAllByParkOwnerIdAndStatus(@Param("parkOwnerId") Long parkOwnerId, @Param("status") BookingStatus status);


}
