package com.pranavagayathri.TableBookingApp.service;

import com.pranavagayathri.TableBookingApp.dto.ReservationDTO;
import com.pranavagayathri.TableBookingApp.dto.ReservationRequestDTO;
import com.pranavagayathri.TableBookingApp.exceptions.*;
import com.pranavagayathri.TableBookingApp.helpermethods.ReservationDTOtoReservation;
import com.pranavagayathri.TableBookingApp.helpermethods.ReservationToReservationDTO;
import com.pranavagayathri.TableBookingApp.model.Reservation;
import com.pranavagayathri.TableBookingApp.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepo;



    @Mock
    private ReservationToReservationDTO reservationToReservationDTO;

    @Mock
    private ReservationDTOtoReservation reservationDTOtoReservation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReservations() throws ReservationNotFoundException, UserNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException {
        // Mocking data
        Reservation reservation1 = new Reservation(1L,  LocalTime.now(),LocalDate.now(),2, null, null, null);
        Reservation reservation2 = new Reservation(2L, LocalTime.now(), LocalDate.now(), 3,null, null, null);

        ReservationDTO reservationDTO1 = new ReservationDTO(1L, LocalTime.now(),LocalDate.now(),  null,1L, "A",1, 4);
        ReservationDTO reservationDTO2 = new ReservationDTO(2L, LocalTime.now(),LocalDate.now(),  null,1L, "A",1, 4);

        when(reservationRepo.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));
        when(reservationToReservationDTO.reservationToReservationDTO(reservation1)).thenReturn(reservationDTO1);
        when(reservationToReservationDTO.reservationToReservationDTO(reservation2)).thenReturn(reservationDTO2);

        // Test
        List<ReservationDTO> result = reservationService.getAllReservations();

        // Verifications
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getReservationId());
        assertEquals(2L, result.get(1).getReservationId());
        verify(reservationRepo, times(1)).findAll();
    }

    @Test
    void testGetReservationByIdSuccess() throws ReservationNotFoundException, UserNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException {
        // Mocking data
        Reservation reservation1 = new Reservation(1L,  LocalTime.now(),LocalDate.now(),2, null, null, null);
        ReservationDTO reservationDTO1 = new ReservationDTO(1L, LocalTime.now(),LocalDate.now(),  null,1L, "A",1, 4);

        when(reservationRepo.findById(1L)).thenReturn(Optional.of(reservation1));
        when(reservationToReservationDTO.reservationToReservationDTO(reservation1)).thenReturn(reservationDTO1);

        // Test
        ReservationDTO result = reservationService.getReservationById(1L);

        // Verifications
        assertEquals(1L, result.getReservationId());
        verify(reservationRepo, times(1)).findById(1L);
    }

    @Test
    void testGetReservationByIdNotFound() {
        // Mocking the behavior when reservation is not found
        when(reservationRepo.findById(1L)).thenReturn(Optional.empty());

        // Test and verify exception
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getReservationById(1L);
        });

        verify(reservationRepo, times(1)).findById(1L);
    }

    @Test
    void testAddReservationSuccess() throws TableAlreadyReservedException, ReservationNotFoundException, UserNotFoundException, RestaurantNotFoundException {
        // Mocking data
        ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO(1L, 1L, LocalDate.now(), LocalTime.now(), 1L, 1, 4);
        Reservation reservation = new Reservation(1L,  LocalTime.now(),LocalDate.now(),2, null, null, null);

        when(reservationDTOtoReservation.reservationDTOtoReservation(reservationRequestDTO)).thenReturn(reservation);
        when(reservationRepo.save(reservation)).thenReturn(reservation);

        // Test
        String result = reservationService.addReservation(reservationRequestDTO);

        // Verifications
        assertEquals("Reservation created successfully.", result);
        verify(reservationRepo, times(1)).save(reservation);
        verify(reservationDTOtoReservation, times(1)).reservationDTOtoReservation(reservationRequestDTO);
    }

    @Test
    void testAddReservationFailure() throws UserNotFoundException, ReservationNotFoundException, TableAlreadyReservedException, RestaurantNotFoundException {
        // Mocking behavior where reservation cannot be created
        ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO(1L, 1L, LocalDate.now(), LocalTime.now(), 1L, 1, 4);
        when(reservationDTOtoReservation.reservationDTOtoReservation(reservationRequestDTO)).thenReturn(null);

        // Test and verify exception
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.addReservation(reservationRequestDTO);
        });

        verify(reservationDTOtoReservation, times(1)).reservationDTOtoReservation(reservationRequestDTO);
    }
}
