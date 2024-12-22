package com.proyectoHotel.best_travel;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.proyectoHotel.best_travel.domain.entities.FlyEntity;
import com.proyectoHotel.best_travel.domain.entities.ReservationEntity;
import com.proyectoHotel.best_travel.domain.entities.TicketEntity;
import com.proyectoHotel.best_travel.domain.entities.TourEntity;
import com.proyectoHotel.best_travel.domain.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class BestTravelApplication /*implements CommandLineRunner*/ {

	/*private final HotelRepository hotelRepository;
	private final FlyRepository flyRepository;
	private final TicketRepository ticketRepository;
	private final ReservationRepository reservationRepository;
	private final TourRepository tourRepository;
	private final CustomerRepository customerRepository;

	public BestTravelApplication(HotelRepository hotelRepository, FlyRepository flyRepository, TicketRepository ticketRepository,
								 ReservationRepository reservationRepository, TourRepository tourRepository, CustomerRepository customerRepository){


		this.hotelRepository = hotelRepository;
		this.flyRepository = flyRepository;
		this.ticketRepository = ticketRepository;
		this.reservationRepository = reservationRepository;
		this.tourRepository = tourRepository;
		this.customerRepository = customerRepository;
	}*/
	public static void main(String[] args) {
		SpringApplication.run(BestTravelApplication.class, args);
	}
	/*@Override
	public void run(String... args) throws Exception {
		var hotel = hotelRepository.findById(7L).get();
		var fly = flyRepository.findById(15L).get();
		var ticket = ticketRepository.findById(UUID.fromString("32345678-1234-5678-4234-567812345678"));
		var reservation = reservationRepository.findById(UUID.fromString("32345678-1234-5678-1234-567812345678"));
		var customer = customerRepository.findById("BBMB771012HMCRR022").get();
		log.info(String.valueOf(fly));
		log.info(String.valueOf(hotel));
		log.info(String.valueOf(ticket));
		log.info(String.valueOf(reservation));
		log.info(String.valueOf(customer));*/

		//this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(f -> System.out.println(f));
		//this.flyRepository.selectBetweenPrice(BigDecimal.valueOf(10),BigDecimal.valueOf(15)).forEach(System.out::println);
		//this.flyRepository.selectOriginDestiny("Grecia", "Mexico").forEach(System.out::println);

		//var fly = flyRepository.findByTicketId(UUID.fromString("12345678-1234-5678-2236-567812345678"));
		//System.out.println(fly);

		//hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100)).forEach(System.out::println);

		//hotelRepository.findByPriceIsBetween(BigDecimal.valueOf(100),
		//									 	BigDecimal.valueOf(150))
		//												.forEach(System.out::println);

		//var hotel = hotelRepository.findByReservationId(UUID.fromString("12345678-1234-5678-1234-567812345678")).get();
		//System.out.println(hotel);

		/*var customer = customerRepository.findById("GOTW771012HMRGR087").orElseThrow();
		log.info("Client name: " + customer.getFullName());

		var fly = flyRepository.findById(11L).orElseThrow();
		log.info("fly: "+fly.getOriginName()+"-"+fly.getDestinyName());
		var hotel = hotelRepository.findById(3L).orElseThrow();
		log.info("Hotel: "+hotel.getName());

		var tour = TourEntity.builder()
				.customer(customer).build();

		var ticket = TicketEntity.builder().id(UUID.randomUUID())
				.price(fly.getPrice().multiply(BigDecimal.TEN))
				.arrivalDate(LocalDateTime.now())
				.departureDate(LocalDateTime.now())
				.purchaseDate(LocalDate.now())
				.customer(customer)
				.tour(tour)
				.fly(fly)
				.build();

		var reservation = ReservationEntity.builder()
				.id(UUID.randomUUID())
				.dateTimeReservation(LocalDateTime.now())
				.dateEnd(LocalDate.now().plusDays(2))
				.dateStart(LocalDate.now().plusDays(1).atStartOfDay())
				.hotel(hotel)
				.customer(customer)
				.tour(tour)
				.totalDays(1)
				.price(hotel.getPrice().multiply(BigDecimal.TEN))
				.build();

		tour.addReservation(reservation);
		tour.updateReservations();

		tour.addTicket(ticket);
		tour.updateTickets();

		//this.tourRepository.save(tour);
		var tourSaved = this.tourRepository.save(tour);
		Thread.sleep(8000);
		this.tourRepository.deleteById(tourSaved.getId());

	}*/
}
